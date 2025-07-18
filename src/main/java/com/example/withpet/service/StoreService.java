package com.example.withpet.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.withpet.dto.ReviewForm;
import com.example.withpet.dto.Store;
import com.example.withpet.entity.ReviewEntity;
import com.example.withpet.entity.StoreEntity;
import com.example.withpet.entity.UserEntity;
import com.example.withpet.repository.ReviewRepository;
import com.example.withpet.repository.StoreRepository;
import com.example.withpet.repository.UserRepository;

@Service
public class StoreService {
	@Autowired StoreRepository storeRepository;
	@Autowired ReviewRepository reviewRepository;
	@Autowired UserRepository userRepository;
	
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
	
	// 리뷰 저장
	public void insertReview(ReviewForm reviewForm) {
		// 파일 이름변경해서 저장
		MultipartFile uploadFile = reviewForm.getUploadFile();
		if (uploadFile != null && !uploadFile.isEmpty()) {
			String ext = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".")+1);
			String fileName = UUID.randomUUID().toString().replace("-", "")+"."+ext;
			File saveFolder = new File("C:/project/file/"+fileName);
			try {
				uploadFile.transferTo(saveFolder);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			reviewForm.setFile(fileName);
		}
		
		ReviewEntity reviewEntity = reviewForm.toEntity();
		StoreEntity storeEntity = storeRepository.findByTitle(reviewForm.getTitle());
		UserEntity userEntity = userRepository.findById(reviewForm.getUserId()).orElse(null);
		
		reviewEntity.setStoreEntity(storeEntity);
		reviewEntity.setUserEntity(userEntity);
		
		reviewRepository.save(reviewEntity);
	}
	
	// 가게에 해당하는 리뷰들
	public List<Map<String,Object>> selectReviewByTitle(String title){
		return reviewRepository.selectReviewByTitle(title);
	}
}
