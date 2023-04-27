package com.mobilapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilapi.dto.UserDto;
import com.mobilapi.entity.UserEntity;
import com.mobilapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity storedUserDEtails = userRepo.findUserByEmai(user.getEmail());
		
		if (storedUserDEtails != null) throw new RuntimeException("Record Already Exists.");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		userEntity.setUserId("testUserId");
		userEntity.setEncryptedPassword("test");
		
		UserEntity storedUserdetails = userRepo.save(userEntity);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUserdetails, returnValue);
		return returnValue;
	}
	
}
