package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller // @compoenent 어노테이션도 포함되어있기 때문에, 스프링 빈 생성 됨
@RequestMapping("/boards")
public class BoardController {

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 글 목록 method
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("boards", boardService.getList());
		return "boards/list";
	}

	// 글 작성 화면 이동 method
	@GetMapping("/write")
	public String boardWrite() {
		return "boards/write";
	}

	// 글 작성 method
	@PostMapping("/write")
	public String write(@ModelAttribute Board board) {
	    boardService.write(board);
	    return "redirect:/boards";
	}

	// 글 상세 보기 method
	@GetMapping("/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute("board", boardService.getDetail(id));
		return "boards/detail";
	}

	// 글 수정 화면 이동 method
	@GetMapping("/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		model.addAttribute("board", boardService.getById(id));
		return "boards/edit";
	}

	// 글 수정 처리
	@PostMapping("/{id}/edit")
	public String edit(@PathVariable Long id, @ModelAttribute Board board) {
		board.setId(id);
		boardService.update(board);
		return "redirect:/boards";
	}

	// 글 삭제
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		boardService.delete(id);
		return "redirect:/boards";
	}

}
