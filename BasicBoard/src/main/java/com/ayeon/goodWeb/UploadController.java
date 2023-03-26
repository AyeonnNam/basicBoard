package com.ayeon.goodWeb;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController {

	@GetMapping("/uploadAjax")
	public void uploadAjax() {

		log.info("upload ajax");

	}
	
	@ResponseBody
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {

		log.info("update ajax post..............");

		//업로드될폴더 
		String uploadFolder = "/Users/nam-ayeon/Desktop/untitledfolder/temp";

		for (MultipartFile multipartFile : uploadFile) {

			log.info("..............................................");
			log.info("UploadFileName: " + multipartFile.getOriginalFilename());
			log.info("UploadFileSize: " + multipartFile.getSize());
			//업로드될파일이름 
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

			
			log.info("only file name : " + uploadFileName);
			File saveFile = new File(uploadFolder,uploadFileName);

			try {

				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
			}

		}
	}

}
