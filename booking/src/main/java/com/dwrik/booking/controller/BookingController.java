package com.dwrik.booking.controller;

import com.dwrik.booking.dto.BookingDto;
import com.dwrik.booking.model.Booking;
import com.dwrik.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Booking> getAll(@RequestHeader Long userId) {
		return bookingService.getAllBookings(userId);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Booking getOne(
			@RequestHeader Long userId,
			@PathVariable("id") Long bookingId
	) {
		return bookingService.getBookingByBookingIdAndUserId(bookingId, userId);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Booking create(
			@RequestHeader Long userId,
			@RequestHeader(name = "Authorization") String bearerToken,
			@RequestBody @Valid BookingDto bookingDto
	) {
		return bookingService.createNewBooking(bearerToken, userId, bookingDto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestHeader Long userId, @PathVariable("id") Long bookingId) {
		bookingService.deleteBookingByBookingIdAndUserId(bookingId, userId);
	}
}
