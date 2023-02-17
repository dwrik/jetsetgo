package com.dwrik.booking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDto {

	@NotNull
	@JsonProperty("number")
	@Size(min = 5, max = 5, message = "flight number must be alphanumeric string of length 5")
	private String flightNumber;

	@NotNull
	@Size(min = 3, max = 3, message = "source must be string of length 3")
	private String source;

	@NotNull
	@Size(min = 3, max = 3, message = "destination must be string of length 3")
	private String destination;

	@NotNull
	@FutureOrPresent
	@Temporal(TemporalType.DATE)
	private Date date;

	@NotNull
	@Min(value = 1000)
	@Max(value = 100000)
	private Integer fare;

	@NotNull
	@Max(value = 150, message = "a flight cannot have more than 150 seats")
	@Min(value = 0, message = "a flight cannot have negative remaining seats")
	private Integer totalSeats;

	public FlightDto() {
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getFare() {
		return fare;
	}

	public void setFare(Integer fare) {
		this.fare = fare;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
}
