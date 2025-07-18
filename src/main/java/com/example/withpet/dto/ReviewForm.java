package com.example.withpet.dto;

import org.springframework.web.multipart.MultipartFile;

import com.example.withpet.entity.ReviewEntity;

import lombok.Data;

@Data
public class ReviewForm {
	private int reviewId;
	private String rating;
	private String comment;
	private String file;
	private MultipartFile uploadFile;
	private int userId;
	private String title;
	private String createDate;
	
	public ReviewEntity toEntity() {
		ReviewEntity entity = new ReviewEntity();
		entity.setRating(this.rating);
		entity.setComment(this.comment);
		entity.setFile(this.file);
		return entity;
	}
}
