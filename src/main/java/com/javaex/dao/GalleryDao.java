package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	SqlSession sqlSession;

	public int fileInsert(GalleryVo gVo) {
		System.out.println("GalleryDao->fileInsert()");
		return sqlSession.insert("gallery.insert", gVo);
	}
}
