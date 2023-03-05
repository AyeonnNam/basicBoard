package com.ayeon.goodWeb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}
