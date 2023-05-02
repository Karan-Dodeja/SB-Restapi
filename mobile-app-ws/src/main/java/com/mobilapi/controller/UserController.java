package com.mobilapi.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobilapi.dto.UserDto;
import com.mobilapi.model.UserDetailsRequestModel;
import com.mobilapi.model.UserDetailsResponseModel;
import com.mobilapi.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/{id}")
	public UserDetailsResponseModel getUser(@PathVariable String id) {
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		
		return returnValue;
	}
	
	@PostMapping()
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}	
	
	@PutMapping
	public String updateUser() {
		return "update user";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user";
	}
	
}
