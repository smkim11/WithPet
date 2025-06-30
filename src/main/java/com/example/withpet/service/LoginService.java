package com.example.withpet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.withpet.dto.User;
import com.example.withpet.mapper.LoginMapper;

@Service
@Transactional
public class LoginService {
	@Autowired LoginMapper loginMapper;
	
	// 중복 아이디 검사
	public String findSameId(String id) {
		return loginMapper.findSameId(id);
	}
}
