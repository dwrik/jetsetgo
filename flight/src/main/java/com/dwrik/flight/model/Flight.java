package com.dwrik.flight.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "flights")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 5, max = 5, message = "flight number must be alphanumeric string of length 5")
	private String number;

	@NotNull
	@Size(min = 3, max = 3, message = "source must be string of length 3")
	private String source;

	@NotNull
	@Size(min = 3, max = 3, message = "destination must be string of length 3")
	private String destination;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date date;

	@NotNull
	@Min(value = 1000)
	@Max(value = 100000)
	private Integer fare;

	@NotNull
	@Max(value = 150, message = "a flight cannot have more than 150 seats")
	@Min(value = 0, message = "a flight cannot have negative remaining seats")
	private Integer remainingSeats;

	public Flight() {
	}

	public Flight(Long id, String number, String source, String destination, Date date, Integer fare, Integer remainingSeats) {
		this.id = id;
		this.number = number;
		this.source = source;
		this.destination = destination;
		this.date = date;
		this.fare = fare;
		this.remainingSeats = remainingSeats;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}
}
