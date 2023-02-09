package com.dwrik.auth.controller;

import com.dwrik.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login() {
        // TODO: implement custom UserDetailsService for loading users by email/username
        // TODO: use spring security's authentication manager for authenticating if possible
        return null;
    }

    @PostMapping("/register")
    public Map<String, String> register() {
        // TODO: create UserDTO object (currently User model)
        // TODO: add validation annotations like @NotNull, @NotEmpty

        // TODO: add @Valid annotation on @RequestBody of post mapping

        // TODO: handle validation exceptions in ControllerAdvice

        // TODO: implement IUserService a.k.a interface for the UserService (register, emailExists)
        // TODO: implement UserService for all service methods
        // TODO: use custom exceptions if possible

        return null;
    }

}
