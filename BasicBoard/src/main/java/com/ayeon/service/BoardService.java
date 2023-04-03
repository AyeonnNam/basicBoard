package com.ayeon.service;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.Literal;

import com.ayeon.domain.BoardAttachVO;
import com.ayeon.domain.BoardVO;
import com.ayeon.domain.Criteria;

public interface BoardService {

		public void register(BoardVO board);
		
		public BoardVO get(Long bno);
		
		//public List<BoardVO> getList();
		
		public List<BoardVO> getList(Criteria cri);
		
		public boolean modify(BoardVO board);
		
		public boolean remove(Long bno);
		
		public int getTotal(Criteria cri);
		
		public List<BoardAttachVO> getAttachList(Long bno);
}
