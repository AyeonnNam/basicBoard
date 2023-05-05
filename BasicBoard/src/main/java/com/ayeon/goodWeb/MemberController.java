package com.ayeon.goodWeb;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ayeon.domain.AuthVO;
import com.ayeon.domain.MemberVO;
import com.ayeon.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
@AllArgsConstructor
public class MemberController {

	private MemberService service;
	
	private PasswordEncoder encoder;
	
	
	@GetMapping("/join")
	public void join() {	
		
	}
	
	@PostMapping("/join")
	public String join(MemberVO member, AuthVO auth, RedirectAttributes rttr ) {
		
		String rawPw = "";
		String encodePw = "";
		rawPw = member.getUserpw();
		encodePw = encoder.encode(rawPw);
		member.setUserpw(encodePw);
		service.memberJoin(member);
		service.memberAuth(auth);
		rttr.addFlashAttribute("result", member.getUserid());
		return "redirect:/board/list";
	}
	
	
}
