package com.example.withpet.dto;

import com.example.withpet.entity.StoreEntity;

import lombok.Data;

@Data
public class Store {
	private int storeId;
	private String title;
	private String category;
	private String address;
	private String link;
	
	public StoreEntity toEntity() {
		StoreEntity entity = new StoreEntity();
		entity.setTitle(this.title);
		entity.setCategory(this.category);
		entity.setAddress(this.address);
		entity.setLink(this.link);
		
		return entity;
	}
}
