package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	GuestbookDao gbDao;
	
	//방명록 삭제
	public int gbDelete(GuestbookVo gbVo) {
		//System.out.println("gbService->gbDelete()");
		return gbDao.gbDelete(gbVo);
	}

	//방명록 추가
	public int addList(GuestbookVo gbVo) {
		//System.out.println("gbService->addListForm()");
		gbVo.setContent(gbVo.getContent().replace("\r\n","<br>")); //줄바꿈 저장
		return gbDao.gbInsert(gbVo);
	}
	
	//방명록 추가(ajax)
	public GuestbookVo addListAjax(GuestbookVo gbVo) {
		//System.out.println("gbService->addListForm()");
		gbVo.setContent(gbVo.getContent().replace("\r\n","<br>")); //줄바꿈 저장
		System.out.println(gbVo);
		int count = gbDao.gbInsert(gbVo);
		//selectKey에서 no를 가져옴
		int no = gbVo.getNo();
		//방금 저장한 1개의 데이터를 가져와서 return
		return gbDao.getGuest(no);
	}
	
	//방명록 폼
	public List<GuestbookVo> getList() {
		//System.out.println("gbService->getList()");
		return gbDao.gbSelect();
	}
}
