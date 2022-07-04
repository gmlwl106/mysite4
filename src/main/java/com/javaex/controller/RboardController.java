package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping(value="/rboard")
public class RboardController {
	
	@Autowired
	private RboardService rboardService;
	

	//게시판 리스트 폼
	@RequestMapping(value="/listForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String listForm(Model model) {
		System.out.println("RboardController->listForm()");
		return "rboard/list";
	}
	
	//게시판 리스트 가져오기
	@ResponseBody
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public List<RboardVo> list() {
		System.out.println("RboardController->list()");
		return rboardService.getList();
	}
	
	
	//게시판 글 읽기
	@RequestMapping(value="/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @PathVariable int no) {
		System.out.println("RboardController->read()");
		RboardVo rbVo = rboardService.read(no);
		model.addAttribute("rbVo", rbVo);
		return "rboard/read";
	}
	
	
	//댓글 달기 폼
	@RequestMapping(value="/commentWriteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String commentWriteForm(Model model, @PathVariable int no) {
		System.out.println("RboardController->commentWriteForm()");
		model.addAttribute(no);
		return "rboard/writeForm";
	}
	
	
	//댓글 추가
	@RequestMapping(value="/comment", method= {RequestMethod.GET, RequestMethod.POST})
	public String comment(@ModelAttribute RboardVo rbVo) {
		System.out.println("RboardController->comment()");
		rboardService.comment(rbVo);
		return "redirect:listForm";
	}
}
