package com.example.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

	@GetMapping("")
	public String list() {
		return "boards/list";
	}


	// 글 목록 method


	// 글 쓰기 화면 이동 method
	@GetMapping("/write")
	public String boardWrite() {
		return "boards/write";
	}

	// 글 쓰기 처리 method

	// 글 상세 보기 method

	// 글 수정 화면 이동 method

	// 글 수정 처리

	// 글 삭제

}
