package com.ayeon.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class JDBCTests {

	@Setter(onMethod_ = @Autowired)
	DataSource dataSource;
	
	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			log.info(" 드라이버 로딩 오류" + e.getMessage());
			e.printStackTrace();
			// TODO: handle exception
		}

	}
	
	@Test
	public void testConnectionPool() throws Exception{
		try {
			Connection conn = dataSource.getConnection();
			log.info("conn" + conn);
			Thread.sleep(1000);
		} catch (Exception e) {
			log.info("커넥션얻기 실패: " + e.getMessage());
		}
		
	}
	
	
//	@Test
//	public void testConnection() {
//		
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/legacyProject?serverTimezone=Asia/Seoul",
//					"root",
//					"mysql");
//			
//			log.info("conn" + conn);
//		} catch (Exception e) {
//			log.info(" 커넥션 얻기실패 " + e.getMessage());
//		}
//		
//	}
	
	
	
}
