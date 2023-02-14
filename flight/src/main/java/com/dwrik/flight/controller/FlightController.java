package com.dwrik.flight.controller;

import com.dwrik.flight.model.Flight;
import com.dwrik.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Iterable<Flight> getAll() {
		return flightService.getAllFlights();
	}

	@GetMapping("/{from}/{to}/{date}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Iterable<Flight> getUsingFromToAndDate(
			@PathVariable(value = "from") String source,
			@PathVariable(value = "to") String destination,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date
	) {
		return flightService.getFlightsUsingSourceAndDestinationAndDate(source, destination, date);
	}

	@PatchMapping("/{id}/reserve")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Map<String, Object> reserveSeat(@PathVariable Long id) {
		Integer totalSeats = flightService.reserveSeat(id);
		return Map.of(
				"status", "successful",
				"totalSeats", totalSeats
		);
	}
}
