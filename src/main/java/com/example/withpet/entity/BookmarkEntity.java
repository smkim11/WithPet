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
@Table(name = "bookmark")
@Getter
@Setter
public class BookmarkEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookmark_id")
	private int bookmarkId;
	
	@Column(name = "create_date")
	@CurrentTimestamp
	private String createDate;
	
	@Column(name = "user_id")
	private int userEntity;
	
	@Column(name = "store_id")
	private int storeEntity;
	
	
}
