package com.ayeon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayeon.domain.Criteria;
import com.ayeon.domain.ReplyPageDTO;
import com.ayeon.domain.ReplyVO;
import com.ayeon.mapper.BoardMapper;
import com.ayeon.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired )
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired )
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		// TODO Auto-generated method stub
		
		log.info("register..........." +vo);
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		// TODO Auto-generated method stub
		log.info("get............" + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		
		log.info("modify............" +  vo);
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		// TODO Auto-generated method stub
		log.info("remove.............." +  rno);
		ReplyVO vo = mapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		// TODO Auto-generated method stub
		log.info("get Reply List of a Board " +  bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		// TODO Auto-generated method stub
		
		return new ReplyPageDTO(mapper.getCountByBno(bno), 
				mapper.getListWithPaging(cri, bno));
	}

	
		
}
