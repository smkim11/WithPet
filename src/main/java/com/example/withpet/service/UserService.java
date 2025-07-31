package com.example.withpet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.withpet.controller.UserController;
import com.example.withpet.dto.User;
import com.example.withpet.entity.PetEntity;
import com.example.withpet.entity.UserEntity;
import com.example.withpet.repository.PetRepository;
import com.example.withpet.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired UserRepository userRepository;
	@Autowired PetRepository petRepository;
	
	// 중복 아이디 검사
	public boolean findSameId(String id) {
		return userRepository.existsById(id);
	}
	
	// 회원가입
	public void signup(@RequestBody User user) {
		UserEntity userEntity = user.toEntity();
		userRepository.save(userEntity);
		
		PetEntity petEntity = new PetEntity();
		petEntity.setPetName(user.getPetName());
		petEntity.setUserEntity(userEntity);
		petRepository.save(petEntity);
	}
	
	// 로그인
	public UserEntity login(User user) {
	    return userRepository.findByIdAndPw(user.getId(), user.getPw());
	}

	
	// 마이페이지(유저 정보)
	public UserEntity myPage(int userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	// 마이페이지(반려견 정보)
	public PetEntity myPagePet(int userId) {
		return petRepository.findByUserEntity(userRepository.findById(userId).orElse(null));
	}
	
	// 개인정보 수정
	public void updateMyPage(User user) {
		UserEntity userEntity = userRepository.findById(user.getUserId()).orElse(null);
		userEntity.setName(user.getName());
		userEntity.setId(user.getId());
		userEntity.setBirth(user.getBirth());
		userEntity.setEmail(user.getEmail());
	}
}
