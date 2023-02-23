package com.dwrik.checkin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Map;

@RestControllerAdvice
public class CheckinControllerAdvisor {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UnableToCheckinException.class)
	public Map<String, Object> handleUnableToCheckinException(UnableToCheckinException e) {
		return Map.of(
				"status", HttpStatus.BAD_REQUEST.value(),
				"error", e.getMessage()
		);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ConstraintViolationException.class, SQLException.class})
	public Map<String, Object> handleCheckinCreationException(RuntimeException e) {
		return Map.of(
				"status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"error", e.getMessage()
		);
	}
}
