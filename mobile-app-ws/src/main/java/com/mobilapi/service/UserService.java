package com.mobilapi.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mobilapi.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto userDto);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId, UserDto user);
	void deletUser(String userId);
	List<UserDto> getUsers(int page, int limit);
}
