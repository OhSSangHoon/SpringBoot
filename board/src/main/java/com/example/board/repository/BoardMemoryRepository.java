package com.example.board.repository;

import com.example.board.domain.Board;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BoardMemoryRepository {

	private final Map<Long, Board> store = new ConcurrentHashMap<>();

	private final Long sequence = 1L;

	public List<Board> findAll() {
		return store.values().stream().toList();
	}
}
