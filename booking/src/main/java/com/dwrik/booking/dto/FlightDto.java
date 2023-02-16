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
}
