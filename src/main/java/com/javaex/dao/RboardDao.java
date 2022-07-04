package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {

	@Autowired
	private SqlSession sqlSession;
	
	//게시판 리스트 가져오기
	public List<RboardVo> getList() {
		return sqlSession.selectList("rboard.getList");
	}
	
	//게시판 글 가져오기
	public RboardVo getBoard(int no) {
		return sqlSession.selectOne("rboard.getBoard", no);
	}
	
	//글 조회수 올리기
	public int hitUpdate(int no) {
		return sqlSession.update("rboard.hitUpdate", no);
	}
}
