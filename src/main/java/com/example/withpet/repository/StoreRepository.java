package com.example.withpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.withpet.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer>{
	StoreEntity findByTitle(String title);
}
