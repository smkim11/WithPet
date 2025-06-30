package com.example.withpet.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.withpet.dto.User;

@Mapper
public interface LoginMapper {
	String findSameId(String id);
}
