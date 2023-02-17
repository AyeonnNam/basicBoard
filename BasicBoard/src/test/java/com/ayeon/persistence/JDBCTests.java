package com.ayeon.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

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
	public void testConnection() {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/legacyProject?serverTimezone=Asia/Seoul",
					"root",
					"mysql");
			
			log.info("conn" + conn);
		} catch (Exception e) {
			log.info(" 커넥션 얻기실패 " + e.getMessage());
		}
		
	}
}
