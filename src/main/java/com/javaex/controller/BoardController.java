package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//글 삭제
	@RequestMapping(value="/delete/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable int no) {
		System.out.println("BoardController->delete()");
		
		boardService.delete(no);
		
		return "redirect:/board/list";
	}
	
	//글 쓰기
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController->write()");
		
		boardService.write(boardVo);
		
		return "redirect:/board/list";
	}
	
	//글 쓰기 폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardController->writeForm()");
		return "/board/writeForm";
	}
	
	//글 읽기
	@RequestMapping(value="/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @PathVariable int no) {
		System.out.println("BoardController->read()");
		
		BoardVo boardVo = boardService.read(no);
		model.addAttribute("boardVo", boardVo);
		
		return "/board/read";
	}

	//게시판 리스트 폼
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String listForm(Model model) {
		System.out.println("BoardController->listForm()");
		
		List<BoardVo> boardList = boardService.getList();
		model.addAttribute("boardList", boardList);
		
		return "/board/list";
	}
}
