package com.ayeon.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ayeon.domain.AuthVO;
import com.ayeon.domain.MemberVO;
import com.ayeon.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
//	@Test
//	public void readTest() {
//		
//		
//		MemberVO vo = mapper.read("admin91");
//		log.info(" === vo ===  : " + vo );
//		vo.getAuthList().forEach(authVO -> log.info(authVO));
//	}
	
	
	//@Test
	public void testRead() {
		
		MemberVO vo = mapper.read("PurpleBoost04");
		
		log.info(" -- 권한  -- : " + vo.getAuthList());
		
	}
	
	//@Test
	public void insertTest() {
		
		MemberVO member = new MemberVO();
		
		member.setUserName("bbb");
		member.setUserid("sss");
		member.setUserpw("sdfdf");
		mapper.insert(member);
		log.info(" - - - - member - - -  : " + member);
		
		}
	
	@Test
	public void insertAuthTest() {
		
		AuthVO auth = new AuthVO();
		//auth.setAuth("ROLE_USER");
		auth.setUserid("sss");
		mapper.insertAuth(auth);
		log.info("-------- authVO --------" +  auth);
		
		}
	
	
	
}
