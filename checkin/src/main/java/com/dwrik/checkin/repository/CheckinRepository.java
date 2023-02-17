package com.dwrik.checkin.repository;

import com.dwrik.checkin.model.Checkin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends CrudRepository<Checkin, Long> {

	Iterable<Checkin> findByFlightId(Long flightId);

	void deleteByBookingId(Long bookingId);
}
