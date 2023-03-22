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
	
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("ss");
		cri.setType("TC");
		log.info("---------------- 다중 검색 ----------------------");
		List<BoardVO> boardVOs = mapper.listWithPaging(cri);
		boardVOs.forEach(board -> log.info(board));
		
		Criteria cri2 = new Criteria();
		cri2.setKeyword("남아연");
		cri2.setType("W");
				log.info("---------------- 단일 검색 ----------------------");
		List<BoardVO> boardVOs2 = mapper.listWithPaging(cri2);
		boardVOs2.forEach(board -> log.info(board));
		
		Criteria cri3 = new Criteria();
		cri3.setPageNum(2);
		cri3.setAmount(10);
		log.info("--------------- 검색 조건이 없는 경우 --------------" );
		List<BoardVO> boardList = mapper.listWithPaging(cri3);
		boardList.forEach(board -> log.info(board));
	}

	
	//	
//	@Test
//	public void testCount() {
//		Criteria cri = new Criteria();
//		cri.setKeyword("남아연");
//		cri.setType("W");
//		int totalCount = mapper.getTotalCount(cri);
//		
//	
//		log.info(".....................total count.............." + totalCount);
//		
//	}
	
	
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
	
//	@Test
//	public void testListWithPaging() {
//		Criteria cri = new Criteria();
//		cri.setAmount(10);
//		cri.setPageNum(3);
//		
//		log.info("testListWithPaging......................................................." );
//		List<BoardVO> list = mapper.listWithPaging(cri);
//		
//		list.forEach(board -> log.info(board));
//		
//		
//		
//	}

}
