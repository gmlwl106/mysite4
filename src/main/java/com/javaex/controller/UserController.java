package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	//필드
	@Autowired
	private UserService userService;
	
	//회원정보 수정
	@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController->modify()");
		
		int count = userService.modify(userVo);
		UserVo authUser = new UserVo();
		authUser.setNo(userVo.getNo());
		authUser.setName(userVo.getName());
		session.setAttribute("authUser", authUser);
		
		return "redirect:/main";
	}
	
	//회원정보 수정폼
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("UserController->modifyForm()");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.modifyForm(authUser.getNo());
		
		model.addAttribute("userVo", userVo);

		return "/user/modifyForm";
	}
	
	//로그아웃
	@RequestMapping(value="/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController->logout()");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	//로그인
	@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController->login()");
		
		UserVo authUser = userService.login(userVo);

		if(authUser != null) {
			System.out.println("<<로그인 성공>>");
			//세션에 저장
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		}
		
		System.out.println("<<로그인 실패>>");
		return "redirect:/user/loginForm?result=fail";
		
	}

	//로그인폼
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController->loginForm()");
		return "/user/loginForm";
	}
	
	//회원가입
	@RequestMapping(value="/join", method={RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController->join()");
		
		userService.join(userVo);
		return "/user/joinOk";
	}
	
	//회원가입폼
	@RequestMapping(value="/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController->joinForm()");
		return "/user/joinForm";
	}
}
