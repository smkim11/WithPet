package com.example.withpet.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.withpet.dto.User;
import com.example.withpet.entity.UserEntity;
import com.example.withpet.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class UserController {
	@Autowired UserService userService;
	
	// 중복 아이디 검사(있으면 true, 없으면 false)
	@GetMapping("/findSameId/{id}")
	public boolean findSameId(@PathVariable String id) {
		return userService.findSameId(id);
	}
	
	// 회원가입
	@PostMapping("/signup")
	public void signup(@RequestBody User user) {
		userService.signup(user);
	}
	
	// 로그인
	@PostMapping("/login")
	public String login(HttpSession httpSession, @RequestBody User user) {
		if(userService.login(user)) {
			httpSession.setAttribute("userId", user.getUserId());
			httpSession.setAttribute("id", user.getId());
			httpSession.setAttribute("name", user.getName());
			httpSession.setAttribute("role", user.getRole());
			return "로그인 성공";
		}
		return "로그인 실패";
	}
	
	// 세션에 저장한 로그인정보
	@GetMapping("/session")
	public ResponseEntity<Map<String, Object>> getSession(HttpSession session) {
	    Map<String, Object> sessionData = new HashMap<>();
	    if (session.getAttribute("userId") != null) {
	        sessionData.put("userId", session.getAttribute("userId"));
	        sessionData.put("id", session.getAttribute("id"));
	        sessionData.put("name", session.getAttribute("name"));
	        sessionData.put("role", session.getAttribute("role"));
	        return ResponseEntity.ok(sessionData);
	    } else {
	        return ResponseEntity.status(401).body(null); // 로그인 안 된 경우
	    }
	}

	// 마이페이지
	@GetMapping("/myPage/{userId}")
	public UserEntity myPage(@PathVariable int userId) {
		return userService.myPage(userId);
	}
	
	// 개인정보 수정
	@PatchMapping("/updateMyPage")
	public void updateMyPage(@RequestBody User user) {
		userService.updateMyPage(user);
	}
}
