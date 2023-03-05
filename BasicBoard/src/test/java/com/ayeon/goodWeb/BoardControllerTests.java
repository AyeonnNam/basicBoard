package com.ayeon.goodWeb;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class BoardControllerTests {

	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@org.junit.Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

	}

//	@Test
//	public void testList() throws Exception {
//
//		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap()
//
//		);
//
//	}
//
//	@Test
//	public void testRegister() throws Exception {
//
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//
//				.param("title", "테스트새글 제목").param("content", "테스트 새글 내용").param("writer", "작가양반")).andReturn()
//				.getModelAndView().getViewName();
//
//		log.info(resultPage);
//
//	}

	@Test
	public void testGet() throws Exception {

		ModelMap modelMap = mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "16")).andReturn()
				.getModelAndView().getModelMap();

		log.info(modelMap);
	}

	@Test
	public void testModify() throws Exception {

		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify").param("bno", "16")
				.param("title", "고양이는 귀엽다").param("content", "똥카인").param("writer", "아연")).andReturn().getModelAndView()
				.getViewName();

		log.info(resultPage);
	}
	
	

}
