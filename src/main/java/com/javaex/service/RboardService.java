package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {

	@Autowired
	private RboardDao rboardDao;
	
	//게시판 리스트 가져오기
	public List<RboardVo> getList() {
		return rboardDao.getList();
	}
	
	
	//게시판 글 읽기
	public RboardVo read(int no) {
		//조회수 올리기
		rboardDao.hitUpdate(no);
		return rboardDao.getBoard(no);
	}
	
	
	//댓글 작성하기
	public int comment(RboardVo rbVo) {
		
		//가져온 값 변수에 저장
		int no = rbVo.getNo();
		int userNo = rbVo.getUserNo();
		String title = rbVo.getTitle();
		String content = rbVo.getContent().replace("<br>", "\r\n");
		
		//같은 그룹글 orderNo +1
		rboardDao.cmtGroupUpdate(no);
		
		//댓글 설정 가져오기
		rbVo = rboardDao.getCmtSetting(no);
		rbVo.setUserNo(userNo);
		rbVo.setTitle(title);
		rbVo.setContent(content);
		System.out.println(rbVo);
		
		
		
		//댓글 등록
		rboardDao.cmtInsert(rbVo);
		
		
		return 0;
	}
}
