package com.ayeon.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ayeon.domain.MemberVO;
@Component
public class MemberValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberVO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "userid", "member.userid.empty","아이디를 입력해주세요.");
		ValidationUtils.rejectIfEmpty(errors, "userpw", "member.userpw.empty","비밀번호를 입력해주세요.");
		ValidationUtils.rejectIfEmpty(errors, "userName", "member.userName.empty","이름을 입력해주세요.");

		MemberVO member = (MemberVO) target;
		
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
          if (!(pattern.matcher(member.getUserid()).matches())) {
             errors.rejectValue("userid", "member.email.invalid","이메일주소를 입력해주세요.");
          }
		
	}

}
