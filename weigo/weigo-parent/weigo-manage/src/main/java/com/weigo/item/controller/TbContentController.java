package com.weigo.item.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.EasyUIDataGrid;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.item.service.TbContentService;
import com.weigo.pojo.TbContent;



@Controller
public class TbContentController {
	@Autowired
    private TbContentService tbContentService;
	@RequestMapping("/content/query/list")
	@ResponseBody
    public EasyUIDataGrid showList(Long categoryId,int page,int rows){
		SecurityUtils.getSubject().checkPermission("page");
		return tbContentService.selectContent(categoryId,page,rows);
	}
	
	@RequestMapping("/content/save")
	@ResponseBody
	public MessageObject insertContent(TbContent content) {
		SecurityUtils.getSubject().checkPermission("page");
		return tbContentService.insertContent(content);
	}
	
	@RequestMapping("/content/delete")
	@ResponseBody
	public MessageObject deleteContent(String ids) {
		SecurityUtils.getSubject().checkPermission("page");
		return tbContentService.deleteContentByIds(ids);
	}
	
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public MessageObject updateContentByContent(TbContent content) {
		SecurityUtils.getSubject().checkPermission("page");
		return tbContentService.updateContentByContent(content);
	}
}
