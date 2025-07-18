package com.example.withpet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WithPetApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(WithPetApplication.class, args);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/file/**").addResourceLocations("file:///C:/project/file/");
	}
}
