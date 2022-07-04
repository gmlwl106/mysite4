package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping(value="/rboard")
public class RboardController {
	
	@Autowired
	private RboardService rboardService;

	//게시판 리스트 폼
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String listForm(Model model) {
		System.out.println("RboardController->listForm()");
		List<RboardVo> rbList = rboardService.getList();
		model.addAttribute("rbList", rbList);
		return "rboard/list";
	}
}
