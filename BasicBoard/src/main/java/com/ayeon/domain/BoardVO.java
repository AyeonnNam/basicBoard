package com.ayeon.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
	
	//댓글의 숫자를 의미하는 인스턴스 변수추가 
	private int replyCnt;
	
	private List<BoardAttachVO> attachList;
	
}


