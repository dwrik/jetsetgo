package com.dwrik.flight.service;

import com.dwrik.flight.model.Flight;

import java.util.Date;

public interface FlightService {

	Iterable<Flight> getAllFlights();

	Flight getFlightById(Long id);

	Iterable<Flight> getFlightsUsingSourceAndDestinationAndDate(String source, String destination, Date date);

	Flight reserveSeat(Long id);

	void vacateSeat(Long id);
}
