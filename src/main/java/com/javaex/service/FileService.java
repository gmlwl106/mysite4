package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileDao;
import com.javaex.vo.FileVo;

@Service
public class FileService {
	
	@Autowired
	private FileDao fileDao;

	//파일 HDD 저장, 파일 정보 (DB 저장) 추출 저장
	public String save(MultipartFile file) {
		System.out.println("FileService->save() file:"+file.getOriginalFilename());
		
		String saveDir = "D:\\javaStudy\\upload"; //집
		//String saveDir = "C:\\javaStudy\\upload"; //학원
		
		//파일 정보 (DB 저장) 추출 저장
		//오리지날파일명, 저장경로+파일(랜덤)명, 파일 사이즈 등 관리
		
		//오리지날 파일명
		String orgName = file.getOriginalFilename();
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		//현재시간+랜덤UUID+확장자
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		//파일경로(디렉토리+저장파일명)
		String filePath = saveDir + "\\" + saveName;
		
		//파일사이즈
		long fileSize = file.getSize();
		
		FileVo fileVo = new FileVo(orgName, saveName, filePath, fileSize);
		System.out.println(fileVo);
		
		
		//DB 저장
		fileDao.fileInsert(fileVo);
		
		
		//(2) 파일저장
		try {
			
			byte[] fileData = file.getBytes();
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saveName;
	}
}
