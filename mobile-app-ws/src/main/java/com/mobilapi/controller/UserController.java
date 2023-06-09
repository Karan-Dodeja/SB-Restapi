package com.mobilapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobilapi.dto.UserDto;
import com.mobilapi.exception.UserServiceException;
import com.mobilapi.model.ErrorMessages;
import com.mobilapi.model.OperationStatus;
import com.mobilapi.model.RequestOperationName;
import com.mobilapi.model.RequestOperationStatus;
import com.mobilapi.model.UserDetailsRequestModel;
import com.mobilapi.model.UserDetailsResponseModel;
import com.mobilapi.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel getUser(@PathVariable String id) {
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		
		// if(userDetails.getFirstName().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		// if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("The Object Is Null");
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}	
	
	@PutMapping(path= "/{id}",consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} ,produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserDetailsResponseModel updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) {
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		
		// if(userDetails.getFirstName().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		// if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("The Object Is Null");
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		UserDto updateUser = userService.updateUser(id, userDto);
		BeanUtils.copyProperties(updateUser, returnValue);
		return returnValue;
	}
	
	@DeleteMapping(path= "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public OperationStatus deleteUser(@PathVariable String id) {
		OperationStatus returnValue = new OperationStatus();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		userService.deletUser(id);
		returnValue.setOperationName(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserDetailsResponseModel> getUsers(@RequestParam(value = "page", defaultValue = "1") int page, 
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<UserDetailsResponseModel> returnValue = new ArrayList<>();
		List<UserDto> users= userService.getUsers(page, limit);
		// Pagination and Limit, Limit can be any depends upon api call
		for(UserDto userDto : users) {
			UserDetailsResponseModel userModel = new UserDetailsResponseModel();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}
		
		return returnValue;
	}
	
}
