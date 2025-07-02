package com.example.withpet.dto;

import com.example.withpet.entity.UserEntity;

import lombok.Data;

@Data
public class User {
	private int userId;
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String role;
	private String email;
	private String createDate;
	private String petName;
	
	public UserEntity toEntity() {
		UserEntity entity = new UserEntity();
		entity.setId(this.id);
		entity.setPw(this.pw);
		entity.setName(this.name);
		entity.setBirth(this.birth);
		entity.setEmail(this.email);
		
		return entity;
	}
}
