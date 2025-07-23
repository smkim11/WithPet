package com.example.withpet.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.withpet.dto.Bookmark;
import com.example.withpet.entity.BookmarkEntity;
import com.example.withpet.entity.StoreEntity;
import com.example.withpet.entity.UserEntity;
import com.example.withpet.repository.BookmarkRepository;
import com.example.withpet.repository.StoreRepository;
import com.example.withpet.repository.UserRepository;

@Service
public class BookmarkService {
	@Autowired BookmarkRepository bookmarkRepository;
	@Autowired UserRepository userRepository;
	@Autowired StoreRepository storeRepository;
	
	// 이미등록 되어있는지 확인
	public boolean findSameStore(int storeId) {
		if(storeRepository.findById(storeId)==null) {
			return true;
		}
		return false;
	}
	
	// 가게 번호 찾기
	public int selectStoreId(String title) {
		return bookmarkRepository.selectStoreId(title);
	}
	
	// 즐겨찾기 등록
	public void insertBookmark(Bookmark bookmark) {
		BookmarkEntity bookmarkEntity = new BookmarkEntity();
		UserEntity userEntity = userRepository.findById(bookmark.getUserId()).orElse(null);
		StoreEntity storeEntity = storeRepository.findById(bookmark.getStoreId()).orElse(null);
		
		bookmarkEntity.setUserEntity(userEntity);
		bookmarkEntity.setStoreEntity(storeEntity);
		
		bookmarkRepository.save(bookmarkEntity);
	}
	
	// 사용자별 즐겨찾기 가게번호
	public List<Integer> selectStoreIdByUserId(int userId){
		return bookmarkRepository.selectStoreIdByUserId(userId);
	}
	
	// 사용자별 즐겨찾기 목록
	public List<Map<String,Object>> selectBookmarkByUserId(int userId){
		return bookmarkRepository.selectBookmarkByUserId(userId);
	}
}
