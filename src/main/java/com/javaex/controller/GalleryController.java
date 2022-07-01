package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Autowired
	GalleryService gService;

	//이미지 등록
	@RequestMapping(value="/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String imgUpload(@ModelAttribute GalleryVo gVo) {
		System.out.println("GalleryController->imgUpload()");
		System.out.println(gVo);
		gService.imgUpload(gVo);
		return "redirect:/gallery/list";
	}
	
	//갤러리 폼
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String galleryForm() {
		System.out.println("GalleryController->galleryForm()");
		List<GalleryVo> gList = gService.getGallery();
		return "/gallery/list";
	}
}
