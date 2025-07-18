package com.example.withpet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.withpet.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer>{
	// 가게에 해당하는 리뷰
	@Transactional
	@Query(nativeQuery = true, 
			value="SELECT u.`name`,r.rating,r.`comment`,r.`file` "
					+"FROM review r INNER JOIN user u ON r.user_id= u.user_id "
					+"WHERE r.store_id=(SELECT store_id FROM store WHERE title=:title)")
	List<Map<String,Object>> selectReviewByTitle(String title);
}
