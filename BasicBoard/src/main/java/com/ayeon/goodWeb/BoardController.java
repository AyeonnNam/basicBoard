package com.ayeon.goodWeb;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ayeon.domain.BoardAttachVO;
import com.ayeon.domain.BoardVO;
import com.ayeon.domain.Criteria;
import com.ayeon.domain.pageDTO;
import com.ayeon.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService service;

	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list...............");
		model.addAttribute("list", service.getList(cri));
		int total = service.getTotal(cri);
		model.addAttribute("pageMaker", new pageDTO(cri, total));

	}
	
	/*게시물의 등록작업은 POST방식으로 처리하지만, 
	 * 화면에서 입력을 받아야 하므로 GET방식으로 
	 * 입력 페이지를 볼 수 있도록 
	 * BoardController에 메서드 추가*/
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("/register........");
		
		if(board.getAttachList() !=null) {
			
			board.getAttachList().forEach(attach -> log.info("attach : " +  attach));
		}
		
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";

	}

	//수정/삭제가 가능한 화면으로 이동하는 것은 조회와 같다.
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get......");
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri")Criteria cri, RedirectAttributes rttr) {
		log.info("/update..");
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list" + cri.getListLink();
	}                                                 

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri")Criteria cri, RedirectAttributes rttr) {
		log.info("/delete");
		
		List<BoardAttachVO> attachList = service.getAttachList(bno);
		
		if (service.remove(bno)) {
			
			deleteFiles(attachList);
			rttr.addFlashAttribute("result", "success");
		}
//		rttr.addAttribute("pageNum", cri.getPageNum());
//		rttr.addAttribute("amount", cri.getAmount());
//		rttr.addAttribute("type", cri.getType());
//		rttr.addAttribute("keyword", cri.getKeyword());
//		
		return "redirect:/board/list"+ cri.getListLink();
	}
	
	//BoardController는 @RestController로 작성되지 않았기 때문에 직접 @ResponseBody를 적용해서 JSON 데이터를 반환하도록 처리 
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> getAttachList(Long bno){
		
		log.info(" ---- getAttachList  ---- bno : " + bno);
		return new ResponseEntity<List<BoardAttachVO>>(service.getAttachList(bno), HttpStatus.OK);
		
	}
	
	public void deleteFiles(List<BoardAttachVO> attachList) {
		
		if(attachList == null || attachList.size() == 0) {
			return;
		}
		
		log.info("delete attach files..................");
		log.info( " --------------- attachList ---------------- " + attachList);
		
		attachList.forEach(attach -> {
			
			try {
				
				Path file = Paths.get("/Users/nam-ayeon/Desktop/untitledfolder/temp/"
							+ attach.getUploadPath() 
								+ "/" 
									+ attach.getUuid()	+ "_" + attach.getFileName());
				
				Files.deleteIfExists(file);
				
				if(Files.probeContentType(file).startsWith("image")) {
					
					 Path thumbNail = Paths.get("/Users/nam-ayeon/Desktop/untitledfolder/temp/"
								+ attach.getUploadPath() 
									+ "/s_" 
										+ attach.getUuid()	+ "_" + attach.getFileName());
					 Files.delete(thumbNail);
				}
			
			}catch (Exception e) {
				log.error("delete file error" + e.getMessage());
			}//end catch
			
		});//end forEach
	
	}

}
