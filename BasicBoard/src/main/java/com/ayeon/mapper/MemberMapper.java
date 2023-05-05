package com.ayeon.mapper;

import com.ayeon.domain.AuthVO;
import com.ayeon.domain.MemberVO;

public interface MemberMapper {
	

	public MemberVO read(String userid);
	
	public void insert(MemberVO vo);
	
	public void insertAuth(AuthVO auth);
	
	public int idCheck(String userid);

	
}
