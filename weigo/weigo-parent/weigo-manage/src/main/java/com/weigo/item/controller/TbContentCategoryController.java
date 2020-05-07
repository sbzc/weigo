package com.weigo.item.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.ZTreeObject;
import com.weigo.item.service.TbContentCategoryService;
import com.weigo.pojo.TbContentCategory;


@Controller
public class TbContentCategoryController {
   
	@Autowired
	private TbContentCategoryService tbContentCategoryService;
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<ZTreeObject> showContentCategory(@RequestParam(defaultValue = "0") Long id) {
		SecurityUtils.getSubject().checkPermission("page");
		return tbContentCategoryService.getContentCategory(id);
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public MessageObject insertContentCategory(TbContentCategory category) {
		SecurityUtils.getSubject().checkPermission("page");
		return tbContentCategoryService.insertContentCategory(category);
	}
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public MessageObject deleteContentCategory(TbContentCategory category) {
		SecurityUtils.getSubject().checkPermission("page");
		return tbContentCategoryService.deleteContentCategory(category);
	}
	@RequestMapping("/content/category/update")
	@ResponseBody
	public MessageObject updateContentCategory(TbContentCategory category) {
		SecurityUtils.getSubject().checkPermission("page");
		return tbContentCategoryService.updateContentCategory(category);
	}
}
