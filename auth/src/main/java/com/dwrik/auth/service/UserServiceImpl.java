package com.dwrik.auth.service;

import com.dwrik.auth.dto.UserDto;
import com.dwrik.auth.exception.InvalidCredentialsException;
import com.dwrik.auth.exception.UserAlreadyExistsException;
import com.dwrik.auth.model.User;
import com.dwrik.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User login(UserDto userDto) {
		Optional<User> user = userRepository.findByEmail(userDto.getEmail());

		if (user.isEmpty()) {
			throw new InvalidCredentialsException("invalid email or password");
		}

		String hash = user.get().getPassword();
		if (!passwordEncoder.matches(userDto.getPassword(), hash)) {
			throw new InvalidCredentialsException("invalid email or password");
		}

		return user.get();
	}

	@Override
	public User registerNewUser(UserDto userDto) {
		if (emailExists(userDto.getEmail())) {
			throw new UserAlreadyExistsException("account with that email already exists");
		}

		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

		return userRepository.save(user);
	}

	private boolean emailExists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
}
