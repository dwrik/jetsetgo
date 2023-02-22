package com.dwrik.flight.config;

import com.dwrik.flight.dto.DeleteDto;
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
	public Consumer<DeleteDto> onFlightIdReceive() {
		return deleteDto -> {
			flightService.vacateSeat(deleteDto.getFlightId());
		};
	}
}
