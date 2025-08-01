package com.example.withpet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.withpet.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer>{
	StoreEntity findByTitle(String title);
	
	// 가게별 리뷰에 작성된 별점 평균, 참여 인원수
	@Query(nativeQuery = true,
	value= "select AVG(rating) avg, count(*) cnt "
			+ "from review "
			+ "where store_id=:storeId")
	List<Map<String,Object>> selectRatingAvg(int storeId);
}
