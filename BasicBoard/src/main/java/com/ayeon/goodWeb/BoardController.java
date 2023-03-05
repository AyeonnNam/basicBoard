package com.ayeon.goodWeb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ayeon.domain.BoardVO;
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
	public void list(Model model) {

		log.info("list...............");
		model.addAttribute("list", service.getList());

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
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";

	}

	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("/get......");
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("/update..");
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");

		}
		return "redirect:/board/list";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("/delete");
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");

		}
		return "redirect:/board/list";
	}

}
