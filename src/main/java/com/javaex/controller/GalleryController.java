package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService gService;
	
	//이미지 삭제
	@ResponseBody
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String galleryRemove(@RequestParam("no") int no) {
		System.out.println("GalleryController->galleryRemove()");
		return gService.galleryRemove(no);
	}
	
	//이미지 1개 정보 가져오기
	@ResponseBody
	@RequestMapping(value="/getImg", method= {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo getImg(Model model, @RequestParam("no") int no) {
		System.out.println("GalleryController->getImg()");
		return gService.getImg(no);
	}

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
	public String galleryForm(Model model) {
		System.out.println("GalleryController->galleryForm()");
		List<GalleryVo> gList = gService.getGallery();
		model.addAttribute("gList", gList);
		return "/gallery/list";
	}
}
