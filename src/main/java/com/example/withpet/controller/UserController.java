package com.example.withpet.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@CrossOrigin(origins = "http://localhost:4000", allowCredentials = "true")
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
	
	@PostMapping("/login")
	public String login(HttpSession httpSession, @RequestBody User user) {
	    UserEntity loggedInUser = userService.login(user);
	    log.info("로그인 user role: {}", loggedInUser.getRole());
	    if(loggedInUser != null) {
	        httpSession.setAttribute("userId", loggedInUser.getUserId());
	        httpSession.setAttribute("id", loggedInUser.getId());
	        httpSession.setAttribute("name", loggedInUser.getName());
	        httpSession.setAttribute("role", loggedInUser.getRole());
	        return "로그인 성공";
	    }
	    return "로그인 실패";
	}

	
	// 세션에 저장한 로그인정보
	@GetMapping("/session")
	public Map<String, Object> session(HttpSession session) {
	    Map<String, Object> result = new HashMap<>();
	    System.out.println("세션 ID: " + session.getId());
	    System.out.println("userId: " + session.getAttribute("userId"));
	    System.out.println("name: " + session.getAttribute("name"));
	    result.put("userId", session.getAttribute("userId"));
	    result.put("id", session.getAttribute("id"));
	    result.put("name", session.getAttribute("name"));
	    result.put("role", session.getAttribute("role"));
	    return result;
	}

	
	// 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession httpSession) {
		httpSession.invalidate();
		return new ResponseEntity<String>("로그아웃",HttpStatus.OK);
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
