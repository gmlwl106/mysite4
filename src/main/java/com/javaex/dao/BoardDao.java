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
	
	//글 쓰기
	public int boardInsert(BoardVo boardVo) {
		System.out.println("BoardDao->boardInsert()");
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	//글 읽기
	public BoardVo getBoard(int no) {
		System.out.println("BoardDao->getBoard()");
		
		return sqlSession.selectOne("board.getBoard", no);
	}
	
	//조회수 올리기
	public int hitUpdate(int no) {
		System.out.println("BoardDao->hitUpdate()");
		
		return sqlSession.update("board.hitUpdate", no);
	}

	//게시판 리스트
	public List<BoardVo> getList() {
		System.out.println("BoardDao->getList()");
		
		return sqlSession.selectList("board.getList");
	}
}
