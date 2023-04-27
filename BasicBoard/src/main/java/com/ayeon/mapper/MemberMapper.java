package com.ayeon.mapper;

import com.ayeon.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String userid);
	
	public void insert(MemberVO vo);
}
