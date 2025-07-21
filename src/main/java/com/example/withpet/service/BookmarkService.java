package com.example.withpet.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	// 즐겨찾기 등록
	public void insertBookmark(Bookmark bookmark) {
		BookmarkEntity bookmarkEntity = new BookmarkEntity();
		UserEntity userEntity = userRepository.findById(bookmark.getUserId()).orElse(null);
		StoreEntity storeEntity = storeRepository.findById(bookmark.getStoreId()).orElse(null);
		
		bookmarkEntity.setUserEntity(userEntity);
		bookmarkEntity.setStoreEntity(storeEntity);
		
		bookmarkRepository.save(bookmarkEntity);
	}
}
