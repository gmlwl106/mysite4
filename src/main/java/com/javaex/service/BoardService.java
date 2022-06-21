package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	//글 읽기
	public BoardVo read(int no) {
		System.out.println("BoardService->getList()");
		return boardDao.getBoard(no);
	}

	//게시판 리스트
	public List<BoardVo> getList() {
		System.out.println("BoardService->getList()");
		
		return boardDao.getList();
	}
}
