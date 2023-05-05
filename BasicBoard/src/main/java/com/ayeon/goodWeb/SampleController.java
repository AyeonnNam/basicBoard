package com.ayeon.goodWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ayeon.domain.AuthVO;
import com.ayeon.domain.MemberVO;
import com.ayeon.service.MemberService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
@RequestMapping("/sample")
public class SampleController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;

	
	@GetMapping("/all")
	public void doAll() {
		
		log.info("do all can access everybody");
		
	}
	
	@GetMapping("/member")
	public void doMember() {
		
		log.info("logined member");
		
	}
	

	@GetMapping("/admin")
	public void doAdmin() {
		
		log.info("admin only");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/annoMember")
	public void doMember2() {
		log.info("logined annotation member");
		
		
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/annoAdmin")
	public void doAdmin2() {
		log.info("admin annotation  only");
	}
	
}
