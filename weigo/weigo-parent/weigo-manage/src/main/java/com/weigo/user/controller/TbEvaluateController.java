package com.weigo.user.controller;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbEvaluate;
import com.weigo.user.service.TbEvaluateService;

@Controller
public class TbEvaluateController {
    @Autowired
	private TbEvaluateService tbEvaluateService;
	@RequestMapping("/user/evaluateSeller")
	@ResponseBody
	public MessageObject evaluateSeller(TbEvaluate tbEvaluate,String OrderItemId) {
		return tbEvaluateService.insertEvaluateByTbEvaluateAndOrderItemId(tbEvaluate,OrderItemId);
	}
	@RequestMapping("/evaluate/delete")
	@ResponseBody
	public MessageObject evaluateDelete(Long id) {
		 SecurityUtils.getSubject().checkPermission("user");
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		if(id==null) {
			mo.setMsg("É¾³ýÊ§°Ü");
			return mo; 
		}
		mo = tbEvaluateService.deleteEvaluateById(id);
		return mo;
	}
	
	@RequestMapping("/user/userEvaluate/{id}.action")
	@ResponseBody
	public PageInfo<TbEvaluate> getUserEvaluate(@PathVariable Long id,String keyword,@RequestParam(defaultValue = "created")String sort,@RequestParam(defaultValue = "desc")String sortOrder,
			   @RequestParam(defaultValue = "10") int pageSize,@RequestParam(defaultValue = "1" )int pageNum) {
		
		return tbEvaluateService.selectUserEvalutePageInfo(id,keyword,sort,sortOrder,pageSize,pageNum);
	}
	
}
