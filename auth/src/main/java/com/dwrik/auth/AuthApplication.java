package com.dwrik.auth;

import com.dwrik.auth.model.User;
import com.dwrik.auth.repository.UserRepository;
import com.dwrik.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${ADMIN_EMAIL}")
	private String adminEmail;

	@Value("${ADMIN_PASSWORD}")
	private String adminPassword;

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Override
	public void run(String... args) {
		userRepository.save(new User(1, adminEmail, passwordEncoder.encode(adminPassword)));
	}
}
