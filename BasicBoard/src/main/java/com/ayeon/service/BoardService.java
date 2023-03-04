package com.ayeon.service;

import java.util.List;

import com.ayeon.domain.BoardVO;

public interface BoardService {

		public void register(BoardVO board);
		
		public BoardVO get(Long bno);
		
		public List<BoardVO> getList();
		
		public boolean modify(BoardVO board);
		
		public boolean remove(Long bno);
}
