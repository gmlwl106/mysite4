package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	SqlSession sqlSession;
	
	//방명록 삭제
	public int gbDelete(GuestbookVo gbVo) {
		System.out.println("gbDao->gbDelete()");
		int count = sqlSession.delete("gb.delete", gbVo);
		return count;
	}
	
	//방명록 추가
	public int gbInsert(GuestbookVo gbVo) {
		System.out.println("gbDao->gbInsert()");
		int count = sqlSession.insert("gb.insert", gbVo);
		return count;
	}

	//방명록 출력
	public List<GuestbookVo> gbSelect() {
		System.out.println("gbDao->gbSelect()");
		List<GuestbookVo> gbList = sqlSession.selectList("gb.select");
		return gbList;
	}


}
