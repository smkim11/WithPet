package com.example.withpet.dto;

import lombok.Data;

@Data
public class Pet {
	private int petId;
	private int userId;
	private String petName;
}
