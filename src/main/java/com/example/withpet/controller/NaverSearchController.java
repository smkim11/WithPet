package com.example.withpet.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.withpet.service.NaverLocalSearchService;

@RestController
public class NaverSearchController {
	@Autowired NaverLocalSearchService naverService;
	
	// 네이버 지역 검색 API
	@GetMapping("/search/{query}")
    public ResponseEntity<String> search(@PathVariable String query) {
        try {
            String result = naverService.searchLocal(query);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("검색 실패");
        }
    }
	
	// 네이버 클라우드 플랫폼 directions 5 길찾기
	@GetMapping("/directions")
	public ResponseEntity<String> getDirections(
	    @RequestParam String start,
	    @RequestParam String goal
	) throws IOException {
	    String route = naverService.getDrivingRoute(start, goal);
	    return ResponseEntity.ok(route);
	}

}
