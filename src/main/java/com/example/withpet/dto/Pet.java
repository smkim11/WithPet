package com.example.withpet.dto;

import com.example.withpet.entity.PetEntity;

import lombok.Data;

@Data
public class Pet {
	private int petId;
	private int userId;
	private String petName;
	
	public PetEntity toEntity(){
		PetEntity entity = new PetEntity();
		entity.setPetName(this.petName);
		
		return entity;
	}
}
