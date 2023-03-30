package com.ayeon.domain;

import lombok.Data;

@Data
public class BoardAttachVO {

	private String uuid;
	private String fileName;
	private String uploadPath;
	private boolean fileType;
	public void setBno(Long bno) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
