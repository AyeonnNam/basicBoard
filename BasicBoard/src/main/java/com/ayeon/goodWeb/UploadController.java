package com.ayeon.goodWeb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ayeon.domain.AttachFileDTO;

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
	@PostMapping(value = "/uploadAjaxAction", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {

		log.info("update ajax post..............");

		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		
		//업로드될폴더 
		String uploadFolder = "/Users/nam-ayeon/Desktop/untitledfolder/temp";
		String uploadFolderPath = getFolder();
		
		//makeFolder
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info(" -- upload Path: --  " + uploadPath);
		if(uploadPath.exists() == false) {
			
			uploadPath.mkdirs();
		}//make yyyy/MM/dd folder 
		
		for (MultipartFile multipartFile : uploadFile) {

			/*log.info("..............................................");
			log.info("UploadFileName: " + multipartFile.getOriginalFilename());
			log.info("UploadFileSize: " + multipartFile.getSize());
			*/

			AttachFileDTO attachDTO = new AttachFileDTO();
			
			//업로드될파일이름 
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

			attachDTO.setFileName(uploadFileName);
			
			log.info(uploadFileName);
			
//			log.info("only file name : " + uploadFileName);

			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("uploadFileName" + uploadFileName);

			try {
				File saveFile = new File(uploadPath,uploadFileName);

				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				
				if(checkImageType(saveFile)) {
					
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail 
					= new FileOutputStream(new File(uploadPath, "s_" +  uploadFileName));
				
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					
					thumbnail.close();
					
				}
				
				// add to list
				list.add(attachDTO);
				
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e.getMessage());
			}

		} //end for 
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
		@GetMapping("/display")
		@ResponseBody
		public ResponseEntity<byte[]> getFile(String fileName){
			
			log.info("------------------- fileName : " + fileName);
			File file = new File("/Users/nam-ayeon/Desktop/untitledfolder/temp/" +  fileName);
			log.info("--------------- file ------------- " +  file);
			
			ResponseEntity<byte[]> result = null;
			
			try {
				
				
				org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
				header.add("Content-Type",Files.probeContentType(file.toPath()));
				result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
				
			}catch (Exception e) {
				
				e.printStackTrace();
				// TODO: handle exception
			}
			
			return result;
		}
		//다운로드
		@GetMapping(value= "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE )
		@ResponseBody
		public ResponseEntity<Resource> downloadFile(String fileName){
			
			
			FileSystemResource resource = new FileSystemResource("/Users/nam-ayeon/Desktop/untitledfolder/temp/" +fileName);
			
			
			String resourceName = resource.getFilename();
			
			//removeUUID 
			String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);
			//log.info("--- resourceOriginalName --- : " + resourceOriginalName);
			
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			
			try {
				
				headers.add("Content-Disposition", "attachment; fileName=" + new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1"));
	
				
			}catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
			
		}
		
		
	//오늘의 날짜를 경로로 만들어 문자열로 반환 
	private String getFolder() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	
		}
	
	//이미지 타입인지 아닌지 검사하는 메서드 
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
