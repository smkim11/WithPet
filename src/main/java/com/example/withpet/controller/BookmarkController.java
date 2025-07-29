package com.example.withpet.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.withpet.dto.Bookmark;
import com.example.withpet.entity.BookmarkEntity;
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
	
	// 가게 번호 검색
	@GetMapping("/selectStoreId")
	public int selectStoreId(@RequestParam String title) {
		return bookmarkService.selectStoreId(title);
	}
	
	// 사용자별 즐겨찾기한 가게번호
	@GetMapping("/selectStoreIdByUserId")
	public List<Integer> selectStoreIdByUserId(@RequestParam int userId){
		return bookmarkService.selectStoreIdByUserId(userId);
	}
	
	// 사용자별 즐겨찾기 목록
	@GetMapping("/bookmarkList")
	public List<Map<String,Object>> selectBookmarkByUserId(@RequestParam int userId){
		return bookmarkService.selectBookmarkByUserId(userId);
	}
	
	// 즐겨찾기 삭제
	@DeleteMapping("/deleteBookmark")
	public void deleteBookmark(@RequestBody Bookmark bookmark) {
		bookmarkService.deleteBookmark(bookmark);
	}
}
