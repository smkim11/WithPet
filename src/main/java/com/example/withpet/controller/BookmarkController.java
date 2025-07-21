package com.example.withpet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.withpet.dto.Bookmark;
import com.example.withpet.service.BookmarkService;
import com.example.withpet.service.StoreService;

@RestController
public class BookmarkController {
	@Autowired StoreService storeService;
	@Autowired BookmarkService bookmarkService;
	
	// 즐겨찾기
	@PostMapping("/insertBookmark")
	public void insertBookmark(@RequestBody Bookmark bookmark) {
		bookmarkService.insertBookmark(bookmark);
	}
	
	
}
