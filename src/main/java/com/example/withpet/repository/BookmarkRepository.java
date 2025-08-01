package com.example.withpet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.withpet.dto.Bookmark;
import com.example.withpet.entity.BookmarkEntity;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Integer>{
	
	// 즐겨찾기 번호조회
	@Query(nativeQuery = true,
			value= "SELECT * "
			+"FROM bookmark where user_id=:userId and store_id=:storeId")
	BookmarkEntity findByUserIdAndStoreId(int userId, int storeId);
	
	
	// 사용자별 즐겨찾기 목록
	@Query(nativeQuery = true,
			value= "SELECT s.title, s.category, s.address, s.link "
			+"FROM bookmark b INNER JOIN store s ON b.store_id = s.store_id "
			+"INNER JOIN user u ON u.user_id = b.user_id "
			+"WHERE u.user_id=:userId")
	List<Map<String,Object>> selectBookmarkByUserId(int userId); 
	
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
