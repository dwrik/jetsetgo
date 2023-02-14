package com.dwrik.flight.service;

import com.dwrik.flight.model.Flight;
import com.dwrik.flight.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
