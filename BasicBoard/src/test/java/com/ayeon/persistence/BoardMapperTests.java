package com.ayeon.persistence;

import javax.swing.border.TitledBorder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ayeon.domain.BoardVO;
import com.ayeon.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	BoardMapper mapper;
//	
//	
//	@Test
//	public void testInsert() {
//		BoardVO board = new BoardVO();
//		board.setTitle("title");
//		board.setContent("content");
//		board.setWriter("ayeon");
//		
//		mapper.insert(board);
//		
//		log.info("insert..............");
//		
//	}
//	
//	@Test
//	public void testInsertSelectKey() {
//		
//		log.info("insertSelectKey..............");
//
//		BoardVO board = new BoardVO();
//		board.setTitle("title01");
//		board.setContent("content01");
//		board.setWriter("ayeon01");
//	
//		mapper.insertSelectKey(board);
//		
//		
//	}
//	
//	
//	@Test
//	public void testRead() {
//		
//		log.info("R......E......A.......D.....");
//		BoardVO read = mapper.read(11L);
//		log.info("...............11번 읽기 :" + read);
//	}
//	
//	
	@Test
	public void testDelete() {
		
		int delete = mapper.delete(11L);
		log.info("지워졌나요 : " +delete);
		
	}

}
