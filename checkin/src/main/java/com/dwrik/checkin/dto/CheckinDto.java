package com.dwrik.checkin.dto;

import javax.validation.constraints.NotNull;

public class CheckinDto {

	@NotNull
	private Long bookingId;

	public CheckinDto() {
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
}
