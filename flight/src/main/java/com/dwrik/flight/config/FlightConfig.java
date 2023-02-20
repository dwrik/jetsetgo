package com.dwrik.flight.config;

import com.dwrik.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class FlightConfig {

	@Autowired
	private FlightService flightService;

	@Bean
	public Consumer<Long> onFlightIdReceive() {
		return id -> {
			flightService.vacateSeat(id);
		};
	}
}
