package com.example.spring.ex01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/*
* 요청 데이터를 받는 방법
* 1. @RequestParam : 쿼리 파라미터(쿼리 스트링) 또는 폼 데이터를 개별 변수로 받는 역할
* 2.
* */


@Slf4j
@RequestMapping("/ex01")
@Controller
public class RequestParamController{
	private String username;

	@GetMapping("/hello")
	public String hello(@RequestParam String message){
		log.info("param: {}", message);
		return "index";
	}

	/*
	* 	@RequestParam Options
	* 	 - required : 필수 파라미터 여부를 설정, 기본값은 false
	* 	 - defaultValue : 파라미터가 없으면 기본값으로 초기화
	* 	 - name : 쿼리 파라미터의 name 속성과 매개변수의 변수명을 다르게 설정
	* */
	@GetMapping("/search")
	public String Search(@RequestParam(required = false) String keyword,
						 @RequestParam(defaultValue = "0") int page,
						 @RequestParam(name = "query", required = false) String q) {

		// input 공백이면 null 출력
		if(keyword.isEmpty()){
			keyword = "null";
		}

		log.info("Get Search keyword = {}", keyword);
		log.info("Get page info {}", page);
		log.info("q: {}", q);
		return "index";
	}

}
