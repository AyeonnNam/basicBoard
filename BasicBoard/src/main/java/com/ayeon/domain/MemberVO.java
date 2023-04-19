package com.ayeon.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	private String userid;
	private String userpw;
	private String userName;
	private boolean enabled;
	
	private Date regaDate;
	private Date updateDate;
	private List<AuthVO> authList;
}
