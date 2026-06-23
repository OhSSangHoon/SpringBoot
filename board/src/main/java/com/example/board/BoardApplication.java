package com.example.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}

/*
	controller
	클라이언트의 요청을 핸들링
	데이터 유효성 체크

	service
	연산, 비즈니스 로직에 대한 처리

	repository
	데이터 조회, 저장, 수정, 삭제
* */