package com.example.withpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.withpet.entity.BookmarkEntity;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Integer>{

}
