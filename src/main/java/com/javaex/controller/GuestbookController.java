package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GuestbookController {

	//방명록 폼
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addListForm() {
		System.out.println("GBController->addListForm");
		return "/guestbook/addList";
	}
}
