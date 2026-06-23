package com.example.spring.ex01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// client가 특정 url을 request하면 request를 받아서 연결할 메서드를 정의
@Slf4j
@Controller
@RequestMapping("/ex01")
public class RequestMappingController {
	@GetMapping("/get")
	public String mains() {
		System.out.println("GetMapping");
		return "index";
	}

	@PostMapping("/post")
	public String main2() {
		System.out.println("PostMapping");
		return "index";
	}
}
