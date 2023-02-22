package com.dwrik.checkin.config;

import com.dwrik.checkin.dto.DeleteDto;
import com.dwrik.checkin.model.PendingCheckin;
import com.dwrik.checkin.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class CheckinConfig {

	@Autowired
	private CheckinService checkinService;

	@Bean
	Consumer<PendingCheckin> onPendingCheckinReceive() {
		return pendingCheckin -> {
			checkinService.createNewPendingCheckin(pendingCheckin);
		};
	}

	@Bean
	Consumer<DeleteDto> onBookingIdReceive() {
		return deleteDto -> {
			checkinService.deleteExistingCheckinOrPendingChecking(deleteDto.getBookingId());
		};
	}
}
