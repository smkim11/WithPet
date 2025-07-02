package com.example.withpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.withpet.entity.PetEntity;
import com.example.withpet.entity.UserEntity;

public interface PetRepository extends JpaRepository<PetEntity, Integer>{
	PetEntity findByUserEntity(UserEntity userEntity);

}
