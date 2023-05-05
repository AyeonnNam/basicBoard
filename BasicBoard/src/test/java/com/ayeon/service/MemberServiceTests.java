package com.ayeon.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ayeon.domain.AuthVO;
import com.ayeon.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
public class MemberServiceTests {

	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwEncoder;
	
	@Test
	public void memberService() {
	
		assertNotNull(service);
		
	}
	
	//@Test
	public void testJoin() {
		
		MemberVO member =new  MemberVO();
		member.setUserid("PurpleBoost04");
		member.setUserName("퍼플4");
		member.setUserpw(pwEncoder.encode("123455"));
		List<AuthVO> authList = new ArrayList<AuthVO>();
		
		AuthVO auth = new AuthVO();
		auth.setUserid("PurpleBoost04");
		auth.setAuth("ROLE_USER");
		
		authList.add(auth);
		
		member.setAuthList(authList);
		
		service.memberJoin(member);
		log.info(" - -- - - member ----------------- " +member);
		
	}
	
	@Test
	public void testAuth() {
		
		AuthVO auth = new AuthVO();
		auth.setUserid("PurpleBoost04");
		auth.setAuth("ROLE_MANAGER");
		service.memberAuth(auth);
		log.info(" - - - - - -권한 주기 성공 - - - - - " );
		
		
		
	}
	
//	@Test
//	public void testRead() {
//		
//		service.
//		
//		}
	
	
}
