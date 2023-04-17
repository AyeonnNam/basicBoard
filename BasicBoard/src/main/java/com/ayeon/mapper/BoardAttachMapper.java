package com.ayeon.mapper;

import java.util.List;

import com.ayeon.domain.BoardAttachVO;
import com.ctc.wstx.shaded.msv_core.reader.datatype.xsd.ListState;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByBno(Long bno);
	
	public void deleteAll(Long bno);
	
	public List<BoardAttachVO> getOldFiles();
	
		
}
