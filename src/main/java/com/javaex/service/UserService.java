package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	//필드
	@Autowired
	private UserDao userDao;
	
	//생성자
	//메소드gs
	
	//메소드 일반
	
	public int modify(UserVo userVo) {
		System.out.println("UserService->modify()");
		
		int count = userDao.userUpdate(userVo);
		
		return count;
	}
	
	public UserVo modifyForm(UserVo authUser) {
		System.out.println("UserService->userSelect()");

		UserVo userVo = userDao.userSelect(authUser);
		
		return userVo;
	}

	public int join(UserVo userVo) {
		System.out.println("UserService->join()");
		int count = userDao.userInsert(userVo);
		
		return count;
	}

	public UserVo login(UserVo userVo) {
		System.out.println("UserService->login()");
		
		UserVo authUser = userDao.getUser(userVo);
		
		return authUser;
	}


}
