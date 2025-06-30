package com.example.withpet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.withpet.service.LoginService;

@RestController
public class LoginRest {
	@Autowired LoginService loginService;
	
	@GetMapping("/findSameId/{id}")
	public boolean findSameId(@PathVariable String id) {
		if(loginService.findSameId(id)==null) {
			return true;
		}
		return false;
	}
}
