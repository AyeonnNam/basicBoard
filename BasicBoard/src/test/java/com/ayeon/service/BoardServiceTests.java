package com.ayeon.service;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ayeon.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void test() {
		log.info("service..............." +service);
		
		assertNotNull(service);
	}
	
//	@Test
//	public void testRegister() {
//		BoardVO board = new BoardVO();
//		board.setTitle("아깝다....");
//		board.setContent("자라트위드자켓을 못산것이...");
//		board.setWriter("아요니");
//		service.register(board);
//		
//		log.info("생성된 게시물의 번호: " + board.getBno());
//	}
	
	@Test
	public void testGet() {
		log.info(service.get(1L));
		
	}
	
	@Test
	public void testGetList() {
		service.getList().forEach(board-> log.info(board));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(1L);
		if(board == null) {
			return;
		}
		
		board.setTitle("제목 수정........");
		service.modify(board);
	}
	
	@Test
	public void testDelete() {
		
		log.info("REMOVE RESULT......................: " + service.remove(3L));
	}
	

}
