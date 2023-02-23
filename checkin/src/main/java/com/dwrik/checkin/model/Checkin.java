package com.dwrik.checkin.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "checkins")
public class Checkin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long bookingId;

	@NotNull
	private Long flightId;

	@Max(value = 150, message = "a flight cannot have more than 150 seats")
	@Min(value = 1, message = "a flight cannot have negative remaining seats")
	private Integer seatNumber;

	@NotNull
	private Boolean checkinStatus;

	public Checkin() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Boolean getCheckinStatus() {
		return checkinStatus;
	}

	public void setCheckinStatus(Boolean checkinStatus) {
		this.checkinStatus = checkinStatus;
	}
}
