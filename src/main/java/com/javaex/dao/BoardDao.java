package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession;

	//게시판 리스트
	public List<BoardVo> getList() {
		System.out.println("BoardDao->getList()");
		
		return sqlSession.selectList("board.getList");
	}
}
