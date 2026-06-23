package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.repository.BoardMemoryRepository;

import java.util.List;

public class BoardService {
	public final BoardMemoryRepository boardRepository;

	// 전체 글 목록
	public List<Board> getList() {
		return boardRepository.findAll();
	}
}
