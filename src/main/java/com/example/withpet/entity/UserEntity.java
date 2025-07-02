package com.example.withpet.entity;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "id")
	private String id;
	
	@Column(name = "pw")
	private String pw;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "birth")
	private String birth;
	
	// 기본값은 user
	@Column(name = "role")
	private String role="user";
	
	@Column(name = "email")
	private String email;
	
	// 오늘날짜 자동입력
	@Column(name = "create_date")
	@CurrentTimestamp
	private String createDate;
}
