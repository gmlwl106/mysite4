package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession;

	//리스트(일반) 페이징
	public List<BoardVo> selectList4(int startRnum, int endRnum) {
		System.out.println("BoardDao->selectList4()");
		
		//원래는 서비스에서 하는 부분
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		System.out.println(map);
		
		return sqlSession.selectList("board.selectList4", map);
	}
	
	//글 전체 갯수
	public int selectTotalCnt() {
		System.out.println("BoardDao->getTotalCnt()");
		return sqlSession.selectOne("board.selectTotalCnt");
	}
	
	//글 수정
	public int boardUpdate(BoardVo boardVo) {
		//System.out.println("BoardDao->boardUpdate()");
		return sqlSession.update("board.update", boardVo);
	}
	
	//글 삭제
	public int boardDelete(int no) {
		//System.out.println("BoardDao->boardDelete()");
		return sqlSession.delete("board.delete", no);
	}
	
	//글 쓰기
	public int boardInsert(BoardVo boardVo) {
		//System.out.println("BoardDao->boardInsert()");
		return sqlSession.insert("board.insert", boardVo);
	}
	
	//글 가져오기
	public BoardVo getBoard(int no) {
		//System.out.println("BoardDao->getBoard()");
		return sqlSession.selectOne("board.getBoard", no);
	}
	
	//조회수 올리기
	public int hitUpdate(int no) {
		System.out.println("BoardDao->hitUpdate()");
		
		return sqlSession.update("board.hitUpdate", no);
	}

	//게시판 리스트 가져오기 (+검색)
	public List<BoardVo> getList(String keyword) {
		//System.out.println("BoardDao->getList()");
		return sqlSession.selectList("board.getList", keyword);
	}

	

	
}
