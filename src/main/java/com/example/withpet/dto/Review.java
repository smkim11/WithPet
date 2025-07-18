package com.example.withpet.dto;

import com.example.withpet.entity.ReviewEntity;

import lombok.Data;

@Data
public class Review {
	private int reviewId;
	private String rating;
	private String comment;
	private String file;
	private int userId;
	private int storeId;
	private String createDate;
	
	public ReviewEntity toEntity() {
		ReviewEntity entity = new ReviewEntity();
		entity.setRating(this.rating);
		entity.setComment(this.comment);
		entity.setFile(this.file);
		return entity;
	}
}
