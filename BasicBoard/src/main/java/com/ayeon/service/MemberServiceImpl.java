package com.ayeon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayeon.domain.AuthVO;
import com.ayeon.domain.MemberVO;
import com.ayeon.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

	@Override
	public void memberJoin(MemberVO vo) {
		
		log.info("---- 회원가입 성공 ----- " + vo);
		
		 mapper.insert(vo);
		
		
		
	}

	@Override
	public void memberAuth(AuthVO auth) {
		
		log.info("---- 멤버 권한 주기 성공 ----- " + auth);
		
		mapper.insertAuth(auth);
		
	}
	
	
	
	
	

	
	
}
