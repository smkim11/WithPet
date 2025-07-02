package com.example.withpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.withpet.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	boolean existsById(String id);
	UserEntity findByIdAndPw(String id, String pw);
	
}
