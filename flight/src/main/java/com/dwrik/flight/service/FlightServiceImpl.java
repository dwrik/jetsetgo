package com.dwrik.flight.service;

import com.dwrik.flight.exception.UnknownFlightException;
import com.dwrik.flight.model.Flight;
import com.dwrik.flight.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;

	public Iterable<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	public Iterable<Flight> getFlightsUsingSourceAndDestinationAndDate(String source, String destination, Date date) {
		return flightRepository.findBySourceAndDestinationAndDate(source, destination, date);
	}

	@Transactional
	public Integer reserveSeat(Long id) {
		Optional<Flight> result = flightRepository.findById(id);

		if (result.isEmpty()) {
			throw new UnknownFlightException("no such flight exists");
		}

		Flight flight = result.get();
		flight.setRemainingSeats(flight.getRemainingSeats() - 1);
		flightRepository.save(flight);

		return flight.getTotalSeats();
	}

	@Transactional
	public void vacateSeat(Long id) {
		Optional<Flight> result = flightRepository.findById(id);

		if (result.isEmpty()) {
			throw new UnknownFlightException("no such flight exists");
		}

		Flight flight = result.get();
		flight.setRemainingSeats(flight.getRemainingSeats() + 1);
		flightRepository.save(flight);
	}
}
