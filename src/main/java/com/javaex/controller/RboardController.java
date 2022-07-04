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
	
	//게시글 작성 폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("RboardController->writeForm()");
		return "rboard/writeForm";
	}
	
	//게시글 작성
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute RboardVo rbVo) {
		System.out.println("RboardController->write()");
		rboardService.write(rbVo);
		return "redirect:listForm";
	}
	
	
	//댓글 작성 폼
	@RequestMapping(value="/commentForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String commentForm(Model model, @PathVariable int no) {
		System.out.println("RboardController->commentForm()");
		model.addAttribute(no);
		return "rboard/commentForm";
	}
	
	
	//댓글 작성
	@RequestMapping(value="/cmtWrite", method= {RequestMethod.GET, RequestMethod.POST})
	public String commentWrite(@ModelAttribute RboardVo rbVo) {
		System.out.println("RboardController->cmtWrite()");
		rboardService.cmtWrite(rbVo);
		return "redirect:listForm";
	}
}
