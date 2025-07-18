package com.example.withpet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.withpet.dto.Bookmark;
import com.example.withpet.repository.BookmarkRepository;

@Service
public class BookmarkService {
	@Autowired BookmarkRepository bookmarkRepository;
	
	// 즐겨찾기 등록
	public void insertBookmark(Bookmark bookmark) {
		
	}
}
