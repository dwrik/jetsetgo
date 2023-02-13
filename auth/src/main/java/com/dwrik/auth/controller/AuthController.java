package com.dwrik.auth.controller;

import com.dwrik.auth.dto.UserDto;
import com.dwrik.auth.model.User;
import com.dwrik.auth.service.UserService;
import com.dwrik.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public Map<String, String> login(@Valid @RequestBody UserDto userDto) {
		User user = userService.login(userDto);

		String id = user.getId().toString();
		String email = user.getEmail();
		String authToken = jwtUtil.generateToken(user.getId(), user.getEmail());

		return Map.of(
				"id", id,
				"email", email,
				"token", authToken
		);
	}

	@PostMapping("/register")
	public Map<String, String> register(@Valid @RequestBody UserDto userDto) {
		User user = userService.registerNewUser(userDto);

		String id = user.getId().toString();
		String email = user.getEmail();

		return Map.of(
				"id", id,
				"email", email
		);
	}
}
