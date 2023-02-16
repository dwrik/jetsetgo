package com.dwrik.booking.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long userId;

	@NotNull
	private Long flightId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
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

	@Max(value = 150, message = "a flight cannot have more than 150 seats")
	@Min(value = 1, message = "a flight cannot have negative remaining seats")
	private Integer seatNumber;

	@NotNull
	private Boolean checkinStatus;

	public Booking() {
	}

	public Booking(Long id, Long userId, Long flightId, String firstName, String lastName, String flightNumber, String source, String destination, Date date, Integer fare, Integer seatNumber, Boolean checkinStatus) {
		this.id = id;
		this.userId = userId;
		this.flightId = flightId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.flightNumber = flightNumber;
		this.source = source;
		this.destination = destination;
		this.date = date;
		this.fare = fare;
		this.seatNumber = seatNumber;
		this.checkinStatus = checkinStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
