package com.dwrik.flight.service;

import com.dwrik.flight.model.Flight;

import java.util.Date;

public interface FlightService {

	Iterable<Flight> getAllFlights();

	Iterable<Flight> getFlightsUsingSourceAndDestinationAndDate(String source, String destination, Date date);
}
