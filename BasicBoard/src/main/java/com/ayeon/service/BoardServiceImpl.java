package com.ayeon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayeon.domain.BoardVO;
import com.ayeon.domain.Criteria;
import com.ayeon.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {
		log.info("register........." + board);
		mapper.insertSelectKey(board);

	}

	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		log.info("get............" + bno);
		return mapper.read(bno);
	}

//	@Override
//	public List<BoardVO> getList() {
//		return mapper.getList();
//	}

	@Override
	public List<BoardVO> getList(Criteria cri){
		return mapper.listWithPaging(cri);
	}
	
	@Override
	public boolean modify(BoardVO board) {

		log.info("modify............." + board);
		return mapper.update(board) == 1;

	}

	@Override
	public boolean remove(Long bno) {
		log.info("delete............." + bno);

		return mapper.delete(bno) == 1;
	}

	@Override
	public int getTotal(Criteria cri) {
		
		return mapper.getTotalCount(cri);
	}

}
