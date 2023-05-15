package com.ayeon.domain;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MemberVO {
	
	
	private Long mno;
	
	private String userid;

	private String userpw;
	
	private String userName;
	private boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList;
}
