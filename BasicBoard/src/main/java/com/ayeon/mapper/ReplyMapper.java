package com.ayeon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ayeon.domain.Criteria;
import com.ayeon.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO reply);
	
	public ReplyVO read(Long rno);
	
	public int delete(Long rno);
	
	public int update(ReplyVO reply);
	
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
}
