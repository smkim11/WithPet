package com.example.withpet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.withpet.dto.Store;
import com.example.withpet.service.StoreService;

@RestController
public class StoreController {
	@Autowired StoreService storeService;
	
	// 가게정보가 저장되어있지 않으면 저장
	@PostMapping("/insertStore")
	public void insertStore(@RequestBody Store store) {
		if(storeService.findSameStore(store.getTitle())) {
			storeService.insertStore(store);
		}
		
	}
}
