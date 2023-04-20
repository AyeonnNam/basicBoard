package com.ayeon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ayeon.domain.MemberVO;
import com.ayeon.mapper.MemberMapper;
import com.ayeon.securiy.domain.CustomUser;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService{

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("==== Load User Bu Username === : " + username);
		
	MemberVO vo = mapper.read(username);
		
	log.warn("queried by member mapper:  " + vo);
	
	return vo ==null ? null: new CustomUser(vo);
	
	}

}
