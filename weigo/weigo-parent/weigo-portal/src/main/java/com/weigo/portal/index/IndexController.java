package com.weigo.portal.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.MessageObject;
@Controller
public class IndexController {
	
	
	@RequestMapping("{page}")
	public String index(@PathVariable String page) {
		return page;
	}
	
	@RequestMapping("/checkLogin")
	@ResponseBody
	public MessageObject checkLogin() {
		
		MessageObject mo = new MessageObject();
		Object principal = SecurityUtils.getSubject().getPrincipal();
		if(principal!=null) {
			mo.setCode(1);
		}else {
			mo.setCode(0);
		}
		return mo;
		
	}
	
	
	
	@RequestMapping("/user/login")
	public String userLogin(Model model,HttpServletRequest req) {
		SavedRequest savedRequest = WebUtils.getSavedRequest(req);
		if(savedRequest==null) {
			
		}else {
			
			HttpSession session = req.getSession();
				session.setAttribute("url", "http://47.103.218.192:8082/portal/index");
			
		}
		return "redirect:http://47.103.218.192:8080/login.jsp";
	}
}
