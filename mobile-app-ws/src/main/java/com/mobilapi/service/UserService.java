package com.mobilapi.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mobilapi.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
}
