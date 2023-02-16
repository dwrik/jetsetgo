package com.dwrik.booking.service;

import com.dwrik.booking.dto.BookingDto;
import com.dwrik.booking.dto.FlightDto;
import com.dwrik.booking.exception.UnableToCreateBookingException;
import com.dwrik.booking.exception.UnknownBookingException;
import com.dwrik.booking.feignclient.FlightClient;
import com.dwrik.booking.model.Booking;
import com.dwrik.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private FlightClient flightClient;

	@Autowired
	private StreamBridge streamBridge;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public Booking getBookingByBookingIdAndUserId(Long bookingId, Long userId) {
		return bookingRepository.findByIdAndUserId(bookingId, userId)
				.orElseThrow(() -> new UnknownBookingException("booking not found"));
	}

	@Override
	public Iterable<Booking> getAllBookings(Long userId) {
		return bookingRepository.findByUserId(userId);
	}

	@Override
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
		booking.setCheckinStatus(false);

		Long bookingId = bookingRepository.save(booking).getId();
		return bookingRepository.findById(bookingId).orElse(booking);
	}

	@Override
	public void deleteBookingByBookingIdAndUserId(Long bookingId, Long userId) {
		Optional<Booking> result = bookingRepository.findByIdAndUserId(bookingId, userId);

		if (result.isEmpty()) {
			throw new UnknownBookingException("booking not found");
		}

		Booking booking = result.get();
		bookingRepository.delete(booking);
		streamBridge.send("booking-deletion", booking.getFlightId());
	}

	private boolean bookingExists(Long userId, Long flightId) {
		return bookingRepository.findByUserIdAndFlightId(userId, flightId).isPresent();
	}
}
