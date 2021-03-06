package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	//리스트(일반) 페이징
	@RequestMapping(value="/list4", method= {RequestMethod.GET, RequestMethod.POST})
	public String list4(Model model, 
						@RequestParam(value="crtPage", required = false, defaultValue = "1") int crtPage,
						@RequestParam(value="keyword", required=false, defaultValue="") String keyword) {
		System.out.println("BoardController->list4()");
		Map<String, Object> boardMap = boardService.getBoardList4(crtPage, keyword);
		model.addAttribute("boardMap", boardMap);
		return "board/list4";
	}
	

	//글 수정
	@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController->modify()");
		
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
	//글 수정폼
	@RequestMapping(value="/modifyForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @PathVariable int no) {
		System.out.println("BoardController->modifyForm()");
		
		BoardVo boardVo = boardService.modifyForm(no);
		model.addAttribute("boardVo", boardVo);
		
		return "board/modifyForm";
	}
	
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
		return "board/writeForm";
	}
	
	//글 읽기
	@RequestMapping(value="/read/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @PathVariable int no) {
		System.out.println("BoardController->read()");
		
		BoardVo boardVo = boardService.read(no);
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}

	//게시판 리스트 폼 (+검색)
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String listForm(Model model,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword) {
		//keyword 디폴트 값을 dispatcherservlet이 설정
		System.out.println("BoardController->listForm()");
		
		List<BoardVo> boardList = boardService.getList(keyword);
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	/*
	//게시판 리스트 검색
	@RequestMapping(value="/search", method= {RequestMethod.GET, RequestMethod.POST})
	public String search(Model model, @RequestParam("keyword") String keyword) {
		System.out.println("BoardController->search()");
		
		List<BoardVo> boardList = boardService.getList(keyword);
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	*/
}
