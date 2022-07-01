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
	
	//회원정보 수정
	public int modify(UserVo userVo) {
		//System.out.println("UserService->modify()");
		return userDao.userUpdate(userVo);
	}
	
	//회원정보 수정폼 (회원 가져오기)
	public UserVo modifyForm(int no) {
		//System.out.println("UserService->modifyForm()");
		return userDao.modifyGetUser(no);
	}

	//회원 가입
	public int join(UserVo userVo) {
		//System.out.println("UserService->join()");
		return userDao.userInsert(userVo);
	}
	
	//아이디 중복체크 (회원가입)
	public String idCheck(String id) {
		//System.out.println("UserService->idCheck()");
		
		UserVo userVo = userDao.userIdSearch(id);
		
		if(userVo == null) {
			//중복된게 없을때 성공
			return "success";
		} else {
			return "fail";
		}
		
	}

	//로그인 (회원 가져오기)
	public UserVo login(UserVo userVo) {
		//System.out.println("UserService->login()");
		return userDao.getUser(userVo);
	}

	


}
