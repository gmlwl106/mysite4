package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	//필드
	@Autowired
	private SqlSession sqlSession;
	
	//회원정보 수정
	public int userUpdate(UserVo userVo) {
		//System.out.println("UserDao->userUpdate()"+userVo);
		return sqlSession.update("user.update", userVo);
	}
	
	//회원정보 가져오기(회원정보 수정)
	public UserVo modifyGetUser(int no) {
		//System.out.println("UserDao->modifyGetUser()");
		return sqlSession.selectOne("user.modifyGetUser", no);
	}
	

	//회원정보 가져오기(로그인)
	public UserVo getUser(UserVo userVo) {
		//System.out.println("UserDao->getUser()");
		return sqlSession.selectOne("user.getUser", userVo);
	}
	
	
	//회원정보 저장(회원가입)
	public int userInsert(UserVo userVo) {
		//System.out.println("UserDao->userInsert()");
		return sqlSession.insert("user.insert", userVo);
	}

	public UserVo userIdSearch(String id) {
		//System.out.println("UserDao->userIdSearch()");
		return sqlSession.selectOne("user.idSearch", id);
	}

}
