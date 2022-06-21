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
		System.out.println("gbService->gbDelete()");
		int count = gbDao.gbDelete(gbVo);
		return count;
	}

	//방명록 추가
	public int addList(GuestbookVo gbVo) {
		System.out.println("gbService->addListForm()");
		int count = gbDao.gbInsert(gbVo);
		return count;
	}
	
	//방명록 폼
	public List<GuestbookVo> addListForm() {
		System.out.println("gbService->addListForm()");
		List<GuestbookVo> gbList = gbDao.gbSelect();
		return gbList;
	}
	

}
