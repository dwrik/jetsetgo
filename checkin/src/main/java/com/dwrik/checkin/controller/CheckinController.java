package com.dwrik.checkin.controller;

import com.dwrik.checkin.dto.CheckinDto;
import com.dwrik.checkin.model.Checkin;
import com.dwrik.checkin.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CheckinController {

	@Autowired
	private CheckinService checkinService;

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Checkin create(@RequestHeader Long userId, @RequestBody CheckinDto checkinDto) {
		return checkinService.createNewCheckin(checkinDto.getBookingId(), userId);
	}
}
