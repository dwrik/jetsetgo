package com.dwrik.checkin.dto;

import javax.validation.constraints.NotNull;

public class DeleteDto {

	@NotNull
	private Long flightId;

	@NotNull
	private Long bookingId;

	public DeleteDto() {
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
}
