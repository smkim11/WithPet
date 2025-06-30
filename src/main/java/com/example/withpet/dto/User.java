package com.example.withpet.dto;

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
}
