package com.ayeon.goodWeb;


import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public void join(@ModelAttribute MemberVO memberVO, Model model) {	
		
		
		
	}
	
	
	//회원가입시 비밀번호 암호화 
	
	@PostMapping("/join")
	public String join(  @Valid MemberVO memberVO, BindingResult bindingResult, AuthVO auth
			) {
		
		
		if(bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for(ObjectError error : errors){
                log.info("errors - - - - - - - : " +  error);
            }
            return "join";
        }
		
		String rawPw = "";
		String encodePw = "";
		rawPw = memberVO.getUserpw();
		encodePw = encoder.encode(rawPw);
		memberVO.setUserpw(encodePw);
		service.memberJoin(memberVO);
		log.info( " - - - ----- - - -- : member : ------   - - -  - - - - "  + memberVO);
		service.memberAuth(auth);
		
        return "redirect:/board/list";
    }
		
		
		
	
	@PostMapping("/memberIdChk")
	@ResponseBody
	public String memberIdChk(String userid) {
		log.info(" - - - 아이디 중복 검사 - - - ");
		int result = service.idCheck(userid);
		log.info(" -- 결과값: -- " +  result);
		if(result !=0) {
			return "fail";	//중복 아이디 존재 
		}else {
			return 
					"success"; //중복 아이디 x 
		}
		
	}
}
