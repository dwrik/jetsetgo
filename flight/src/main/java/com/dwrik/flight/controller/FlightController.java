package com.dwrik.flight.controller;

import com.dwrik.flight.model.Flight;
import com.dwrik.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/")
	public Iterable<Flight> getAll() {
		return flightService.getAllFlights();
	}

	@GetMapping("/{from}/{to}/{date}")
	public Iterable<Flight> getUsingFromToAndDate(
			@PathVariable(value = "from") String source,
			@PathVariable(value = "to") String destination,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date
	) {
		System.out.println(source + ", " + destination + ", " + date);
		return flightService.getFlightsUsingSourceAndDestinationAndDate(source, destination, date);
	}
}
