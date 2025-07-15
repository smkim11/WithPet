package com.example.withpet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.withpet.dto.Store;
import com.example.withpet.entity.StoreEntity;
import com.example.withpet.repository.ReviewRepository;
import com.example.withpet.repository.StoreRepository;

@Service
public class StoreService {
	@Autowired StoreRepository storeRepository;
	@Autowired ReviewRepository reviewRepository;
	
	// 가게 정보 저장
	public void insertStore(Store store) {
		StoreEntity entity = store.toEntity();
		storeRepository.save(entity);
	}
	
	// 이미등록 되어있는지 확인
	public boolean findSameStore(String title) {
		if(storeRepository.findByTitle(title)==null) {
			return true;
		}
		return false;
	}
}
