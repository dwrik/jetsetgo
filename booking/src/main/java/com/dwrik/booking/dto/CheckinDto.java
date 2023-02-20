package com.dwrik.booking.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CheckinDto {

	@NotNull
	private Long bookingId;

	@NotNull
	private Long userId;

	@NotNull
	@Max(value = 150, message = "a flight cannot have more than 150 seats")
	@Min(value = 1, message = "a flight cannot have negative remaining seats")
	private Integer seatNumber;

	public CheckinDto() {
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
}
