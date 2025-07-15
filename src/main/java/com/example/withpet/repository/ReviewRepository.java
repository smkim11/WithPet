package com.example.withpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.withpet.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer>{

}
