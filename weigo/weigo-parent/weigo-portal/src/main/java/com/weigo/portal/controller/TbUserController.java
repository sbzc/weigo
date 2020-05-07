package com.weigo.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.pojo.TbEvaluate;
import com.weigo.portal.service.TbEvaluateService;

@Controller
public class TbUserController {

	@Autowired
	private TbEvaluateService tbEvaluateService;
	@RequestMapping("/user/userEvaluate/{username}.action")
	@ResponseBody
	public List<TbEvaluate> getUserEvaluate(@PathVariable String username) {
		try {
			username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return tbEvaluateService.selectUserEvaluateByUsernmae(username);
	}
}
