package com.example.withpet.entity;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "review")
@Getter
@Setter
public class ReviewEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private int reviewId;
	
	@Column(name = "rating")
	private String rating;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "file")
	private String file;
	
	@Column(name = "create_date")
	@CurrentTimestamp
	private String createDate;
	
	@ManyToOne 
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	
	@ManyToOne
	@JoinColumn(name="store_id")
	private StoreEntity storeEntity;
}
