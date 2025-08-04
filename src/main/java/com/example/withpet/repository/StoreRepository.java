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
	
	// 즐겨찾기 많이된 순위
	@Query(nativeQuery = true,
			value= "SELECT s.title, ROW_NUMBER() OVER(ORDER BY COUNT(*) DESC) AS ranking "
					+ "FROM bookmark b INNER JOIN store s ON b.store_id = s.store_id "
					+ "GROUP BY s.title LIMIT 0,10")
	List<Map<String,Object>> selectStoreRankByBookmark();
	
	// 리뷰많은 순위
	@Query(nativeQuery = true,
			value= "SELECT s.title, ROW_NUMBER() OVER(ORDER BY COUNT(*) DESC) AS ranking "
					+ "FROM review r INNER JOIN store s ON r.store_id = s.store_id "
					+ "GROUP BY s.title LIMIT 0,10")
	List<Map<String,Object>> selectStoreRankByReview();
}
