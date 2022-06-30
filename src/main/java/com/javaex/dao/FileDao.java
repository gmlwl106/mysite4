package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public int fileInsert(FileVo fileVo) {
		return sqlSession.insert("files.insert", fileVo);
	}
}
