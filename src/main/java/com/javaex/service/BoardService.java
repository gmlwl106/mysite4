package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	
	//리스트(일반) 페이징
	public Map<String, Object> getBoardList4(int crtPage, String keyword) {
		System.out.println("BoardService->getBoardList4()");
		
		//페이지당 글 갯수
		int listCnt = 10;
		
		//현재페이지
		crtPage = (crtPage>0)? crtPage : (crtPage=1); //삼항연산자
		/*if(crtPage > 0) {} else {crtPage = 1;}*/
		
		int startRnum = (crtPage-1)*listCnt+1; //시작번호
		int endRnum = (startRnum + listCnt)-1; //끝번호
		
		List<BoardVo> boardList = boardDao.selectList4(startRnum, endRnum, keyword);
		System.out.println(boardList);
		
		//=======================페이징 계산======================================================
		
		//전체글 갯수
		int totalCnt = boardDao.selectTotalCnt(keyword);
		
		//페이지당 버튼 갯수
		int pageBtnCnt = 5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage / (double)pageBtnCnt)*pageBtnCnt;
		
		//시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo-pageBtnCnt) + 1;
		
		//다음 화살표 유무
		boolean next = false;
		if((listCnt*endPageBtnNo) < totalCnt) {
			next = true;
		} else {
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		
		
		return pMap;
	}
	
	//글 수정
	public int modify(BoardVo boardVo) {
		//System.out.println("BoardService->modify()");
		boardVo.setContent(boardVo.getContent().replace("\r\n","<br>")); //줄바꿈 저장
		return boardDao.boardUpdate(boardVo);
	}
	
	//글 수정폼
	public BoardVo modifyForm(int no) {
		//System.out.println("BoardService->modifyForm()");
		BoardVo boardVo = boardDao.getBoard(no);
		boardVo.setContent(boardVo.getContent().replace("<br>", "\r\n")); //줄바꿈
		return boardVo;
	}
	
	//글 삭제
	public int delete(int no) {
		//System.out.println("BoardService->delete()");
		return boardDao.boardDelete(no);
	}
	
	//글 쓰기
	public int write(BoardVo boardVo) {
		//System.out.println("BoardService->write()");
		boardVo.setContent(boardVo.getContent().replace("\r\n","<br>")); //줄바꿈 저장
		return boardDao.boardInsert(boardVo);
		/*for(int i=1; i<=52; i++) {
			boardVo.setTitle("전체게시글 제목입니다 a");
			boardVo.setContent("전체 게시글 내용 입니다");
			boardDao.boardInsert(boardVo);*/
		//return 1;
		
	}
	
	//글 읽기
	public BoardVo read(int no) {
		//System.out.println("BoardService->getList()");
		boardDao.hitUpdate(no);
		return boardDao.getBoard(no);
	}

	//게시판 리스트 (+검색)
	public List<BoardVo> getList(String keyword) {
		//System.out.println("BoardService->getList()");
		return boardDao.getList(keyword);
	}

	
}
