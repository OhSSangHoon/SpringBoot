package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.repository.BoardMemoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



/*
 * 	스프링 빈 생성 방법
 * 		1. 객체 주입
 * 		2. 생성자 주입
 * 		3. setter 주입
 * */

@Slf4j
@Service
@RequiredArgsConstructor // 생성자 주입을 받겠다
public class BoardService {

//	// 필드 주입 장식
////	@Autowired
	public BoardMemoryRepository boardRepository;
//

//	// 생성자 주입 방식 (多)
	@Autowired
	public BoardService(BoardMemoryRepository boardRepository) {
		this.boardRepository = boardRepository;
		log.info("BoardService 생성");

	}

//	// setter 주입 방식
//	@Autowired
//	public void setBoardRepository(BoardMemoryRepository boardRepository){
//		this.boardRepository = boardRepository;
//	}


	// 전체 글 목록
	public List<Board> getList() {
		return boardRepository.findAll();
	}

	// 상세 조회
	public Board getById(Long id){
		return boardRepository.findById(id);
	}

	// 상세 조회 (조회수 증가)
	public Board getDetail(Long id) {
		Board board = boardRepository.findById(id);
		if (board != null) {
			board.setViewCount(board.getViewCount() + 1);
			boardRepository.save(board);
		}
		return board;
	}

	// 게시글 작성
	public Board write(Board board) {
		if(board != null){
			board.setId(null);
			board.setViewCount(0);
			board.setCreateAt(LocalDateTime.now());
		}
		return boardRepository.save(board);
	}

	public Board update(Board board) {
		Board existing = getById(board.getId());
		existing.setTitle(board.getTitle());
		existing.setWriter(board.getWriter());
		existing.setContent(board.getContent());
		return boardRepository.save(existing);
	}

	// 게시글 삭제
	public Board delete(Long id){

		return boardRepository.delete(id);
	}
}
