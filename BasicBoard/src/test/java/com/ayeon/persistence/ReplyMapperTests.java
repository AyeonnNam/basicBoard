package com.ayeon.persistence;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ayeon.domain.ReplyVO;
import com.ayeon.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTests {

	
	@Setter(onMethod_ = @Autowired)
	 ReplyMapper mapper;
	 
	 
	 private Long[] bnoArr = {54531094L, 54531092L, 54531091L, 54531090L, 54531089L};
	 
	 @Test
	 public void testMapper() {
		 
		 log.info(mapper);
		 
	 }
	 
	
	 
//	 @Test
//	 public void testCreate() {
//		 
//		 IntStream.rangeClosed(1, 10).forEach(i->{
//			 
//			 ReplyVO vo = new ReplyVO();
//			 vo.setBno(bnoArr[i%5]);
//			 vo.setReply("댓글테스트" +i);
//			 vo.setReplyer("남아연" +i);
//			 mapper.insert(vo);
//			 
//		 });
//	 }
// 
//
	@Test
	public void testRead() {
		
		ReplyVO read = mapper.read(1L);
		log.info("------------------- 댓글 : " +  read);
				
		
	}
//	
//	@Test
//	public void testUpdate() {
//		
//		Long targetRno = 10L;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		
//		vo.setReply("메롱 메롱 메롱");
//		int count = mapper.update(vo);
//		log.info("---------- UPDATE COUNT: -------" + count);		
//	}
//	
//	@Test
//	public void testDelete() {
//		
//	
//		
//		log.info("------------ Delete Count : ------ " +  mapper.delete(1L));
//	}
//	
	
	
	
}
