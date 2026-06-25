package com.example.board.repository;

import com.example.board.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/*
* 스프링 빈으로 등록
* 	1. @Component
* 	2. @Configuration @Bean
* */

@Slf4j
@Component
public class BoardMemoryRepository {
	private final Map<Long, Board> store = new ConcurrentHashMap<>();
	private Long sequence = 1L;

	public BoardMemoryRepository() {
		log.info("BoardMemoryRepository 생성");

		Board board1 = new Board();
		board1.setId(sequence);
		board1.setTitle("안녕하세요. 첫 번째 글입니다.");
		board1.setContent("첫 번째 글의 내용입니다 안녕하세요.");
		board1.setWriter("삼상훈");
		board1.setCreateAt(LocalDateTime.now());

		store.put(sequence++, board1);

		Board board2 = new Board();
		board2.setId(sequence);
		board2.setTitle("안녕하세요. 두 번째 글입니다.");
		board2.setContent("두 번째 글의 내용입니다 안녕하세요.");
		board2.setWriter("사상훈");
		board2.setCreateAt(LocalDateTime.now());

		store.put(sequence++, board2);

		Board board3 = new Board();
		board3.setId(sequence);
		board3.setTitle("안녕하세요. 세 번째 글입니다.");
		board3.setContent("세 번째 글의 내용입니다 안녕하세요.");
		board3.setWriter("오상훈");
		board3.setCreateAt(LocalDateTime.now());

		store.put(sequence++, board3);
	}

	// 모든 게시글 조회
	public List<Board> findAll() {
		// return type이 Collection
		return store.values().stream().toList();
	}

	// 상세 조회
	public Board findById(Long id){
		return store.get(id);
	}

	// 게시글 저장 및 수정
	public Board save(Board board){
		if(board.getId() == null){
			board.setId(sequence++);
		}
		store.put(board.getId(), board);

		return board;
	}

	// 게시글 삭제
	public Board delete(Long id){
		return store.remove(id);
	}
}
