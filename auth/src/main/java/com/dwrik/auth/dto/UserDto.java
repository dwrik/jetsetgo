package com.dwrik.auth.dto;

import javax.validation.constraints.*;

public class UserDto {

	@NotNull
	@Email(message = "invalid email address")
	private String email;

	@NotNull
	@Size(min = 8, max = 15, message = "password should be between 8 and 15 characters long")
	private String password;

	public UserDto() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
