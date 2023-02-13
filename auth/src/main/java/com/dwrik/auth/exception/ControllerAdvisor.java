package com.dwrik.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	Map<String, Object> handleDtoExceptions(MethodArgumentNotValidException e) {
		Map<String, String> map = new HashMap<>();
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			map.put(error.getField(), error.getDefaultMessage());
		}
		return Map.of("error", map);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({UserAlreadyExistsException.class, InvalidCredentialsException.class})
	Map<String, String> handleUserExceptions(RuntimeException exception) {
		return Map.of("error", exception.getMessage());
	}
}
