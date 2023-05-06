package com.ayeon.mapper;

import com.ayeon.domain.AuthVO;
import com.ayeon.domain.MemberVO;

public interface MemberMapper {
	

	public MemberVO read(String userid);
	
	public void insert(MemberVO vo);
	
	public void insertAuth(AuthVO auth);
	
	//아이디 중복 체크 
	public int idCheck(String userid);

	
}
