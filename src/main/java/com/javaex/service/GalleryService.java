package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	GalleryDao gDao;
	
	
	
	//갤러리 전체 가져오기
	public List<GalleryVo> getGallery() {
		//System.out.println("GalleryService->getGallery()");
		return gDao.getGallery();
	}
	
	//이미지 1개 정보 가져오기
	public GalleryVo getImg(int no) {
		System.out.println("GalleryService->getImg()");
		return gDao.getImg(no);
	}

	//파일 정보 추출 저장
	public void imgUpload(GalleryVo gVo) {
		System.out.println("GalleryService->imgUpload()");
		
		String saveDir = "D:\\javaStudy\\upload"; //집
		//String saveDir = "C:\\javaStudy\\upload"; //학원
		
		//오리지날파일명
		String orgName = gVo.getFile().getOriginalFilename();
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		//현재시간+랜덤UUID+확장자
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		//파일경로(디렉토리+저장파일명)
		String filePath = saveDir + "\\" + saveName;
		
		//파일사이즈
		long fileSize = gVo.getFile().getSize();
		
		//gVo에 추가
		gVo.setOrgName(orgName);
		gVo.setSaveName(saveName);
		gVo.setFilePath(filePath);
		gVo.setFileSize(fileSize);
		
		System.out.println(gVo);
		
		//DB 저장
		gDao.fileInsert(gVo);
		
		//파일 저장
		try {
			byte[] fileData = gVo.getFile().getBytes();
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//이미지 삭제
	public String galleryRemove(int no) {
		System.out.println("GalleryService->galleryRemove()");
		int count = gDao.galleryDelete(no);
		
		if(count > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}
