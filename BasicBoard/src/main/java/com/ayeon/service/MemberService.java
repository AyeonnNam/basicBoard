package com.ayeon.service;


import com.ayeon.domain.AuthVO;
import com.ayeon.domain.MemberVO;


public interface MemberService {
	
	
	
	
	public void memberJoin(MemberVO vo);
	
	public void memberAuth(AuthVO vo);
	
//	public MemberVO memberRead(MemberVO vo);

}
