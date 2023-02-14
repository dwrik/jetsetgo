package com.dwrik.auth.service;

import com.dwrik.auth.dto.UserDto;
import com.dwrik.auth.model.User;

public interface UserService {

	User login(UserDto userDto);

	User registerNewUser(UserDto user);
}
