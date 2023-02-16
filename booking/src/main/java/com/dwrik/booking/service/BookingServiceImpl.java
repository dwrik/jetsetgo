package com.dwrik.booking.service;

import com.dwrik.booking.dto.BookingDto;
import com.dwrik.booking.exception.UnableToCreateBookingException;
import com.dwrik.booking.exception.UnknownBookingException;
import com.dwrik.booking.feignclients.FlightClient;
import com.dwrik.booking.model.Booking;
import com.dwrik.booking.repository.BookingRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private FlightClient flightClient;

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

		try {
			Map<String, Object> response = flightClient.reserveSeat(
					Map.of("Authorization", bearerToken),
					bookingDto.getFlightId()
			);
		} catch (FeignException e) {
			throw new UnableToCreateBookingException("failed to create booking");
		}

		Booking booking = new Booking();

		booking.setUserId(userId);
		booking.setFlightId(bookingDto.getFlightId());
		booking.setFirstName(bookingDto.getFirstName());
		booking.setLastName(bookingDto.getLastName());
		booking.setFlightNumber(bookingDto.getFlightNumber());
		booking.setSource(bookingDto.getSource());
		booking.setDestination(bookingDto.getDestination());
		booking.setDate(bookingDto.getDate());
		booking.setFare(bookingDto.getFare());
		booking.setCheckinStatus(false);

		Long id = bookingRepository.save(booking).getId();
		return bookingRepository.findById(id).get();
	}

	@Override
	public void deleteBookingByBookingIdAndUserId(Long bookingId, Long userId) {
		Optional<Booking> result = bookingRepository.findByIdAndUserId(bookingId, userId);

		if (result.isEmpty()) {
			throw new UnknownBookingException("booking not found");
		}

		Booking booking = result.get();
		bookingRepository.delete(booking);
	}

	private boolean bookingExists(Long userId, Long flightId) {
		return bookingRepository.findByUserIdAndFlightId(userId, flightId).isPresent();
	}
}
