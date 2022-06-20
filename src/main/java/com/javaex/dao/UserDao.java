package com.javaex.dao;

import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	public void userInsert(UserVo userVo) {
		System.out.println("UserDao->UserInsert()");
		
		
	}

}
