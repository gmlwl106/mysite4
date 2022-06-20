package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	//필드
	@Autowired
	UserDao userDao;
	
	//생성자
	//메소드gs
	
	//메소드 일반

	public void join(UserVo userVo) {
		System.out.println("UserService->join()");
		userDao.userInsert(userVo);
	}

}
