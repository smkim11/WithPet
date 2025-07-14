package com.example.withpet.dto;

import com.example.withpet.entity.ReviewEntity;

import lombok.Data;

@Data
public class Review {
	private int reviewId;
	private String rating;
	private String comment;
	private int userId;
	
	public ReviewEntity toEntity() {
		ReviewEntity entity = new ReviewEntity();
		entity.setRating(this.rating);
		entity.setComment(this.comment);
		
		return entity;
	}
}
