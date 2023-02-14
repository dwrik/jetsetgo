package com.dwrik.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Map;

@RestControllerAdvice
public class FlightControllerAdvisor {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public Map<String, Object> handleInvalidDateException(IllegalArgumentException e) {
		return Map.of(
				"status", HttpStatus.BAD_REQUEST.value(),
				"error", "invalid date"
		);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({UnknownFlightException.class, ConstraintViolationException.class, SQLException.class})
	public Map<String, Object> handleUpdateException(RuntimeException e) {
		return Map.of(
				"status", "failed",
				"error", "invalid flight or unable to reserve seat"
		);
	}
}
