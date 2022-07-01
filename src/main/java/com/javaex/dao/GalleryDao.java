package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	SqlSession sqlSession;

	//갤러리 전체 가져오기
	public List<GalleryVo> getGallery() {
		System.out.println("GalleryDao->getGallery()");
		return sqlSession.selectList("gallery.getGallery");
	}
	
	//이미지 1개 정보 가져오기
	public GalleryVo getImg(int no) {
		System.out.println("GalleryDao->getImg()");
		return sqlSession.selectOne("gallery.getImg", no);
	}
	
	//파일정보 저장
	public int fileInsert(GalleryVo gVo) {
		System.out.println("GalleryDao->fileInsert()");
		return sqlSession.insert("gallery.insert", gVo);
	}
}
