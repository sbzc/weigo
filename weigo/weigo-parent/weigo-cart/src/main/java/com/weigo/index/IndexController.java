package com.weigo.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	
	@RequestMapping("{page}")
	public String indexPage(@PathVariable String page) {
		
		return page;
	}
	
	@RequestMapping("/user/login")
	public String userLogin(Model model,HttpServletRequest req) {
		SavedRequest savedRequest = WebUtils.getSavedRequest(req);
		if(savedRequest==null) {
			
		}else {
			
			HttpSession session = req.getSession();
			session.setAttribute("url", "http://47.103.218.192:8084"+savedRequest.getRequestUrl());
		}
		return "redirect:http://47.103.218.192:8080/login.jsp";
	}
}
