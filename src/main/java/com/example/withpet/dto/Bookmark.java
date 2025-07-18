package com.example.withpet.dto;

import lombok.Data;

@Data
public class Bookmark {
	private int bookmarkId;
	private int userId;
	private int storeId;
	private String createDate;
}
