package com.ayeon.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ayeon.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class timeMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private TimeMapper timeMapper;
	
	@Test
	public void getTimeTest() {
		
		log.info("timeMapper" + timeMapper.getTime());
		
		
	}
	
	
	@Test
	public void getTimeTest2() {
		
		log.info("timeMapper: =================== " + timeMapper.getTime2());
		
		
	}
}
