package com.ayeon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ayeon.domain.BoardVO;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno >0 ")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);

}
