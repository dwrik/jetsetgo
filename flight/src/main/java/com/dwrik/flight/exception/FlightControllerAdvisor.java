package com.dwrik.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class FlightControllerAdvisor {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public Map<String, Object> handleInvalidDateExceptions(IllegalArgumentException e) {
		return Map.of(
				"status", HttpStatus.BAD_REQUEST.value(),
				"error", "invalid date"
		);
	}
}
