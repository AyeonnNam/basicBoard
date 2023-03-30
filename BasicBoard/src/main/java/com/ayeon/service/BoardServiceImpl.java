package com.ayeon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayeon.domain.BoardVO;
import com.ayeon.domain.Criteria;
import com.ayeon.mapper.BoardAttachMapper;
import com.ayeon.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service

public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired )
	private BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired )
	private BoardAttachMapper attachMapper;

	@Transactional
	@Override
	public void register(BoardVO board) {
		log.info("register........." + board);
		
		mapper.insertSelectKey(board);
		
		if(board.getAttachList() ==null || board.getAttachList().size() <=0) {
			return ;
			
		}
		
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
		
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
