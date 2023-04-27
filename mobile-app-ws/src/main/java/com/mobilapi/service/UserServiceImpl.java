package com.mobilapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		// TODO Auto-generated method stub
		return null;
	}
	
}
