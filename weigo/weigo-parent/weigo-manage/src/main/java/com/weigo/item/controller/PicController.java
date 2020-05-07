package com.weigo.item.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.weigo.item.service.PicService;


@Controller
public class PicController {
	@Autowired
	private PicService picService;
	@RequestMapping("pic/upload")
	@ResponseBody
	public Map<String,Object> upload(MultipartFile uploadFile){
		System.out.println("Í¼Æ¬ÉÏ´«");
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			 map = picService.upload(uploadFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("´íÎó£º"+e.getMessage());
			map.put("error", 1);
			map.put("message",e.getMessage());
		}
		return map;
	}
}
