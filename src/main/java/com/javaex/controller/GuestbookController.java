package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/gb")
public class GuestbookController {
	
	@Autowired
	GuestbookService gbService;
	
	//방명록 삭제
	public String deleteForm() {
		
		return "";
	}

	//방명록 추가
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(@ModelAttribute GuestbookVo gbVo) {
		System.out.println("GBController->addList()");
		
		int count = gbService.addList(gbVo);
		
		return "redirect:/gb/addList";
	}
	
	//방명록 폼
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addListForm(Model model) {
		System.out.println("GBController->addListForm()");
		
		List<GuestbookVo> gbList = gbService.addListForm();
		model.addAttribute("gbList", gbList);
		
		return "/guestbook/addList";
	}
}
