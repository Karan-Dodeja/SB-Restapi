package com.mobilapi.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mobilapi.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findUserByEmail(String email);
	UserEntity findByUserId(String userId);
	void delete(UserEntity userEntity);
	UserEntity save(UserEntity userEntity);
	
}
