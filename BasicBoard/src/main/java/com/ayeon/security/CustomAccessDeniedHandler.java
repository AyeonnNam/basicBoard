package com.ayeon.security;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAccessDeniedHandler {
	
	
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessException ) 
			
			throws Exception {
		
		log.error("Accedd Denied Handler");
		
		log.error("Redirect....");
				
		response.sendRedirect("/accessError");
				
	}
	
	
}
