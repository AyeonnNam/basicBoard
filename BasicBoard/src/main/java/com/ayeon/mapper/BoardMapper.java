package com.ayeon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ayeon.domain.BoardVO;
import com.ayeon.domain.Criteria;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno >0 ")
	public List<BoardVO> getList();
	
	public List<BoardVO> listWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);

	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
}


