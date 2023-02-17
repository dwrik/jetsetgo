package com.dwrik.booking.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookingControllerAdvisor {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String, String> map = new HashMap<>();
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			map.put(error.getField(), error.getDefaultMessage());
		}
		return Map.of("error", map);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({UnknownBookingException.class, UnableToCreateBookingException.class})
	public Map<String, Object> handleUnknownBookingAndUnableToCreateBookingException(RuntimeException e) {
		return Map.of(
				"status", HttpStatus.BAD_REQUEST.value(),
				"error", e.getMessage()
		);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.class)
	public Map<String, Object> handleFeignException(FeignException e) {
		return Map.of(
				"status", HttpStatus.BAD_REQUEST.value(),
				"error", "failed to get seat confirmation"
		);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ConstraintViolationException.class, SQLException.class})
	public Map<String, Object> handleCreateOrDeleteException(RuntimeException e) {
		return Map.of(
				"status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"error", "something went wrong"
		);
	}
}
