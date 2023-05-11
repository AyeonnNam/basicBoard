package com.ayeon.goodWeb;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ayeon.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"	
})
public class MemberControllerTests {

	
	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@org.junit.Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

	}

	
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder pwEncoder;

	@Test
	public void validTests() throws Exception{
		
		
		String viewName = mockMvc.perform(MockMvcRequestBuilders.post("/member/join")
				.param("userid", "dldldldl").param("userpw", "").param("userName", "")).andReturn().getModelAndView().getViewName();
		
		log.info("viewName --- " + viewName);
				
//		String userid="아이유";
//		String userpw="asdasd";
//		String encodePw = pwEncoder.encode(userpw);
//		String userName=null;
//		MemberVO member = new MemberVO();
//		member.setUserid(userid);
//		member.setUserpw(encodePw);
//		member.setUserName(userName);
//		
		
		
		
	}
}
