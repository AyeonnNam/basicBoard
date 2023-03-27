package com.ayeon.domain;

import lombok.Data;

@Data
public class AttachFileDTO {

	/* 브라우저로 전송해야 하는 데이터 
	 * -업로드된 파일의 이름과 원본 파일의 이름 
	 * -파일이 저장된 경로 
	 * -업로드된 파일이 이미지인지 아닌지에 대한 정
	 * 
	 * */
	
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
	
}
