package com.dwrik.booking.config;

import com.dwrik.booking.dto.CheckinDto;
import com.dwrik.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class BookingConfig {

	@Autowired
	private BookingService bookingService;

	@Bean
	public Consumer<CheckinDto> onCheckinDtoReceive() {
		return checkinDto -> {
			bookingService.updateCheckinStatus(checkinDto);
		};
	}
}
