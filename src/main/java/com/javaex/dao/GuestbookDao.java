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
		//System.out.println("gbDao->gbDelete()");
		return sqlSession.delete("gb.delete", gbVo);
	}
	
	//방명록 추가
	public int gbInsert(GuestbookVo gbVo) {
		//System.out.println("gbDao->gbInsert()");
		return sqlSession.insert("gb.insert", gbVo);
	}

	//방명록 출력
	public List<GuestbookVo> gbSelect() {
		//System.out.println("gbDao->gbSelect()");
		return sqlSession.selectList("gb.select");
	}
	
	//방명록 1개 데이터 가져오기 (ajax)
	public GuestbookVo getGuest(int no) {
		System.out.println("gbDao->getGuest()");
		return sqlSession.selectOne("gb.getGuest", no);
	}


}
