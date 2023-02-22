package com.dwrik.booking.service;

import com.dwrik.booking.dto.BookingDto;
import com.dwrik.booking.dto.CheckinDto;
import com.dwrik.booking.dto.FlightDto;
import com.dwrik.booking.exception.UnableToCreateBookingException;
import com.dwrik.booking.exception.UnknownBookingException;
import com.dwrik.booking.feignclient.FlightClient;
import com.dwrik.booking.model.Booking;
import com.dwrik.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private FlightClient flightClient;

	@Autowired
	private StreamBridge streamBridge;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	@Transactional(readOnly = true)
	public Booking getBookingByBookingIdAndUserId(Long bookingId, Long userId) {
		return bookingRepository.findByIdAndUserId(bookingId, userId)
				.orElseThrow(() -> new UnknownBookingException("booking not found"));
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Booking> getAllBookings(Long userId) {
		return bookingRepository.findByUserId(userId);
	}

	@Override
	@Transactional
	public Booking createNewBooking(String bearerToken, Long userId, BookingDto bookingDto) {
		if (bookingExists(userId, bookingDto.getFlightId())) {
			throw new UnableToCreateBookingException("booking already exists");
		}

		Map<String, Object> headerMap = Map.of("Authorization", bearerToken);
		FlightDto flightDto = flightClient.reserveSeat(headerMap, bookingDto.getFlightId());

		Booking booking = new Booking();

		booking.setUserId(userId);
		booking.setFlightId(bookingDto.getFlightId());
		booking.setFirstName(bookingDto.getFirstName());
		booking.setLastName(bookingDto.getLastName());
		booking.setFlightNumber(flightDto.getFlightNumber());
		booking.setSource(flightDto.getSource());
		booking.setDestination(flightDto.getDestination());
		booking.setDate(flightDto.getDate());
		booking.setFare(flightDto.getFare());
		booking.setCheckinStatus(Boolean.FALSE);

		Booking saved = bookingRepository.save(booking);

		streamBridge.send("pending-checkin", Map.of(
				"bookingId", saved.getId(),
				"userId", saved.getUserId(),
				"flightId", saved.getFlightId(),
				"totalSeats", flightDto.getTotalSeats()
		));

		return bookingRepository.findById(saved.getId()).get();
	}

	@Override
	public Booking updateCheckinStatus(CheckinDto checkinDto) {
		Booking booking = bookingRepository.findByIdAndUserId(checkinDto.getBookingId(), checkinDto.getUserId())
				.orElseThrow(() -> new UnknownBookingException("booking not found"));

		booking.setCheckinStatus(Boolean.TRUE);
		booking.setSeatNumber(checkinDto.getSeatNumber());

		return bookingRepository.save(booking);
	}

	@Override
	@Transactional
	public void deleteBookingByBookingIdAndUserId(Long bookingId, Long userId) {
		Booking booking = bookingRepository.findByIdAndUserId(bookingId, userId)
				.orElseThrow(() -> new UnknownBookingException("booking not found"));

		bookingRepository.delete(booking);
		streamBridge.send("booking-deletion", bookingId);
	}

	@Transactional(readOnly = true)
	private boolean bookingExists(Long userId, Long flightId) {
		return bookingRepository.findByUserIdAndFlightId(userId, flightId).isPresent();
	}
}
