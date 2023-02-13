package com.dwrik.flight.repository;

import com.dwrik.flight.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
	Iterable<Flight> findBySourceAndDestinationAndDate(String source, String destination, Date date);
}
