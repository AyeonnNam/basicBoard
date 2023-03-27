package com.ayeon.goodWeb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ctc.wstx.shaded.msv_core.grammar.xmlschema.Field;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

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
		
		//makeFolder
		File uploadPath = new File(uploadFolder, getFolder());
		log.info(" -- upload Path: --  " + uploadPath);
		if(uploadPath.exists() == false) {
			
			uploadPath.mkdirs();
		}//make yyyy/MM/dd folder 
		
		

		
		for (MultipartFile multipartFile : uploadFile) {

			/*log.info("..............................................");
			log.info("UploadFileName: " + multipartFile.getOriginalFilename());
			log.info("UploadFileSize: " + multipartFile.getSize());
			*/
			
			//업로드될파일이름 
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

			
//			log.info("only file name : " + uploadFileName);

			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath,uploadFileName);

			try {

				multipartFile.transferTo(saveFile);
				
				if(checkImageType(saveFile)) {
					
					FileOutputStream thumbnail 
					= new FileOutputStream(new File(uploadPath, "s_" +  uploadFileName));
				
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					
					thumbnail.close();
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
			}

		}
	}
	
	//오늘의 날짜를 경로로 만들어 문자열로 반환 
	private String getFolder() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	
		}
	
	//이미지 타입인지 검사하는 메서드 
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
