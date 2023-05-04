package com.mobilapi.service;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobilapi.dto.UserDto;
import com.mobilapi.entity.UserEntity;
import com.mobilapi.exception.UserServiceException;
import com.mobilapi.model.ErrorMessage;
import com.mobilapi.model.ErrorMessages;
import com.mobilapi.repository.UserRepository;
import com.mobilapi.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private Utils util;
	
	@Autowired
	private UserEntity userEntity;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity storedUserDEtails = userRepo.findUserByEmail(user.getEmail());
		
		if (storedUserDEtails != null) throw new RuntimeException("Record Already Exists.");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		String publicUserId = util.generateUserId(30);
		
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		UserEntity storedUserdetails = userRepo.save(userEntity);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUserdetails, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findUserByEmail(username);
		
		if(userEntity == null) throw new UsernameNotFoundException(username);
		
		return new User(username, userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepo.findUserByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		UserDto returnvalue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnvalue);
		
		return returnvalue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto returnvalue = new UserDto();
		UserEntity userEntity = userRepo.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		BeanUtils.copyProperties(userEntity, returnvalue);
		return returnvalue;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		UserDto returnvalue = new UserDto();
		UserEntity userEntity = userRepo.findByUserId(userId);
		
		if(userEntity == null) 
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		userEntity.setFirstName(user.getFirstName());
		userEntity.setFirstName(user.getLastName());
		
		UserEntity updatedUserDetails = userRepo.save(userEntity);
		
		BeanUtils.copyProperties(updatedUserDetails, returnvalue);
		
		return returnvalue;
	}

	
	
}
