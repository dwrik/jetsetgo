package com.dwrik.flight.controller;

import com.dwrik.flight.model.Flight;
import com.dwrik.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Flight> getAll() {
		return flightService.getAllFlights();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Flight getOne(@PathVariable Long id) {
		return flightService.getFlightById(id);
	}

	@GetMapping("/{from}/{to}/{date}")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Flight> getUsingFromToAndDate(
			@PathVariable(value = "from") String source,
			@PathVariable(value = "to") String destination,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
	) {
		return flightService.getFlightsUsingSourceAndDestinationAndDate(source, destination, date);
	}

	@PostMapping("/{id}/reserve")
	@ResponseStatus(HttpStatus.CREATED)
	public Flight reserveSeat(@PathVariable Long id) {
		return flightService.reserveSeat(id);
	}
}
