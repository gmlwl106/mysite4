package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/gb")
public class GuestbookController {
	
	@Autowired
	private GuestbookService gbService;
	
	//방명록 삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String gbDelete(@ModelAttribute GuestbookVo gbVo) {
		System.out.println("gbController->gbDelete()");
		
		gbService.gbDelete(gbVo);
		
		return "redirect:/gb/addList";
	}
	
	//방명록 삭제폼
	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable int no) {
		System.out.println("gbController->deleteForm()");
		
		return "guestbook/deleteForm";
	}

	//방명록 추가
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(@ModelAttribute GuestbookVo gbVo) {
		System.out.println("gbController->addList()");
		
		gbService.addList(gbVo);
		
		return "redirect:/gb/addList";
	}
	
	//방명록 폼
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addListForm(Model model) {
		System.out.println("gbController->addListForm()");
		
		List<GuestbookVo> gbList = gbService.getList();
		model.addAttribute("gbList", gbList);
		
		return "guestbook/addList";
	}
}
