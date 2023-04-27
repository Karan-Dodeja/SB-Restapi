package com.mobilapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilapi.dto.UserDto;
import com.mobilapi.entity.UserEntity;
import com.mobilapi.repository.UserRepository;
import com.mobilapi.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private Utils util;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity storedUserDEtails = userRepo.findUserByEmail(user.getEmail());
		
		if (storedUserDEtails != null) throw new RuntimeException("Record Already Exists.");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		// String publicUserId = util.generateUserId(30);
		
		userEntity.setUserId("test user Id");
		userEntity.setEncryptedPassword("test");
		
		UserEntity storedUserdetails = userRepo.save(userEntity);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUserdetails, returnValue);
		return returnValue;
	}
	
}
