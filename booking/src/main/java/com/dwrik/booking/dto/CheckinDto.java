package com.dwrik.booking.dto;

import javax.validation.constraints.NotNull;

public class CheckinDto {

	@NotNull
	private Long bookingId;

	@NotNull
	private Boolean checkinStatus;

	public CheckinDto() {
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Boolean getCheckinStatus() {
		return checkinStatus;
	}

	public void setCheckinStatus(Boolean checkinStatus) {
		this.checkinStatus = checkinStatus;
	}
}
