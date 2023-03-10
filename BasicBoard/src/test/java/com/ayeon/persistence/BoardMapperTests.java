package com.ayeon.persistence;

import static org.hamcrest.CoreMatchers.both;

import java.util.List;

import javax.swing.border.TitledBorder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ayeon.domain.BoardVO;
import com.ayeon.domain.Criteria;
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
//	@Test
//	public void testDelete() {
//		
//		int delete = mapper.delete(11L);
//		log.info("지워졌나요 : " +delete);
//		
//	}
//	
//	@Test
//	public void testUpdate() {
//		
//		BoardVO board= mapper.read(10L);
//		board.setContent("압둘라압둘라압둘라압둘라압둘라");
//		
//		log.info(mapper.update(board));
//		
//		
//	}
	
	@Test
	public void testListWithPaging() {
		Criteria cri = new Criteria();
		cri.setAmount(10);
		cri.setPageNum(3);
		
		List<BoardVO> list = mapper.listWithPaging(cri);
		
		list.forEach(board -> log.info(board));
		
		
		
	}

}
