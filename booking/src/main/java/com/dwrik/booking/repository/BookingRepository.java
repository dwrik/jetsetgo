package com.dwrik.booking.repository;

import com.dwrik.booking.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

	Iterable<Booking> findByUserId(Long userId);

	Optional<Booking> findByIdAndUserId(Long bookingId, Long userId);

	Optional<Booking> findByUserIdAndFlightId(Long userId, Long flightId);
}
