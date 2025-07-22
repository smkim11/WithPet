package com.example.withpet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.withpet.entity.BookmarkEntity;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Integer>{
	 
	// 가게이름에 맞는 가게 번호
	@Query(nativeQuery = true,
	value= "select store_id storeId "
			+ "from store "
			+ "where title like :title")
	int selectStoreId(String title); 
	
	// 사용자별 즐겨찾기한 가게번호
	@Query(nativeQuery = true,
	value= "select store_id storeId "
			+ "from bookmark "
			+ "where user_id = :userId")
	List<Integer> selectStoreIdByUserId(int userId);
}
