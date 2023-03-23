package com.ayeon.mapper;

import org.springframework.test.web.servlet.result.PrintingResultHandler;

import com.ayeon.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO reply);
	
	public ReplyVO read(Long rno);
	
	public int delete(Long rno);
	
	public int update(ReplyVO reply);
	
}
