package com.example.withpet.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.withpet.dto.ReviewForm;
import com.example.withpet.dto.Store;
import com.example.withpet.service.StoreService;

@RestController
public class StoreController {
	@Autowired StoreService storeService;
	
	// 가게정보가 저장되어있지 않으면 저장
	@PostMapping("/insertStore")
	public void insertStore(@RequestBody Store store) {
		if(storeService.findSameStore(store.getTitle())) {
			storeService.insertStore(store);
		}
	}
	
	// 리뷰 저장 (사진도 보내기위해 ModelAttribute 사용)
	@PostMapping("/insertReview")
	public void insertReview(@ModelAttribute ReviewForm reviewForm) {
		storeService.insertReview(reviewForm);
	}
	
	// 가게에 해당하는 리뷰
	@GetMapping("/selectReview")
	public List<Map<String,Object>> selectReviewByTitle(@RequestParam String title){
		return storeService.selectReviewByTitle(title);
	}
	
	// 가게별 리뷰에 작성된 별점 평균, 참여 인원수
	@GetMapping("/selectRatingAvg")
	public List<Map<String,Object>> selectRatingAvg(@RequestParam int storeId) {
		return storeService.selectRatingAvg(storeId);
	}
	
	// 즐겨찾기 많은 순위
	@GetMapping("/selectStoreRankByBookmark")
	public List<Map<String,Object>> selectStoreRankByBookmark() {
		return storeService.selectStoreRankByBookmark();
	}
	
	// 리뷰 많은 순위
	@GetMapping("/selectStoreRankByReview")
	public List<Map<String,Object>> selectStoreRankByReview() {
		return storeService.selectStoreRankByReview();
	}
}
