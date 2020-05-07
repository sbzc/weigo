package com.weigo.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.UserMessage;
import com.weigo.pojo.TbUser;
import com.weigo.user.service.TbUserService;

@Controller
public class TbUserController {
    @Autowired
	private TbUserService tbUserService;
	
	@RequestMapping("/user/addCheckPhone")
	@ResponseBody
	public boolean addCheckPhone(String phone) {
		if(phone==null) {
			return false;
		}
		return tbUserService.addCheckPhone(phone);
	}
	   
	@RequestMapping("/user/addCheckEmail")
	@ResponseBody
	public boolean addCheckEmail(String email) {
		if(email==null) {
			return false;
		}
		return tbUserService.addCheckEmail(email);
	}
	 @RequestMapping("/user/checkUsername")
	 @ResponseBody
	 public boolean checkUsername(String username) {
		 if(username==null)
		 {
			 return false;
		 }
		 int row = tbUserService.selectUserByUsername(username);
		 if(row==1) {
			 return true;
		 }else {
			 return false;
		 }
	 }
	
	
	 @RequestMapping("/user/update")
	 @ResponseBody
	 public MessageObject updateUserByTbUser(TbUser tbUser) {
		 TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		 tbUser.setRoleId(null);
		 tbUser.setEmail(null);
		 tbUser.setId(user.getId());
		 MessageObject mo = new MessageObject();
		
		mo = this.tbUserService.updateUserByTbUser(tbUser);
         
		 return mo;
	 }
	 //用户自己编辑
	 @RequestMapping("/user/myUserEdit")
	 public String showUserById(Model model) {
		TbUser token = (TbUser) SecurityUtils.getSubject().getPrincipal();
		
		 TbUser user= tbUserService.selectUserById(token.getId());
		 model.addAttribute("user", user);
		 return "userEdit";
	 }
	
	
	 
	 @RequestMapping("/user/userMessage")
	 public String  getUserMessage(Long id,Model model) {
		 UserMessage userMessage = this.tbUserService.getUserMessageByUid(id);
		 model.addAttribute("userMessage", userMessage);
		 return "userMessage";
	 }
	 @RequestMapping("/user/dispatcherMessage")
	 public String getDispatcherMessage(String orderItemId,Model model) {
		 UserMessage userMessage = this.tbUserService.getUserMessageByOrderItemId(orderItemId);
		 model.addAttribute("userMessage", userMessage);
		 return "userMessage";
	 }
	 
	 @RequestMapping("/user/getMailCode")
	 @ResponseBody
	 public MessageObject getMailCode(String email,HttpServletRequest req) {
		 MessageObject mo = new MessageObject();
		mo = this.tbUserService.getMailCodeByEmail(email,req);
		 
		 return mo;
	 }
	@RequestMapping("/user/insert")
	@ResponseBody
	public MessageObject insertUser(TbUser user,String verifyCode,HttpServletRequest req) {
		
		return this.tbUserService.insertUserByTbUserAndverifyCode(user,verifyCode,req);
		
	}
	@RequestMapping("/user/getMailCodeAndCheckEmail")
	@ResponseBody
	public MessageObject getMailCodeAndCheckEmail(String email,HttpServletRequest req) {
		return this.tbUserService.getMailCodeByEmailAndCheckEmail(email,req);
	}
	@RequestMapping("/user/editPassword")
	@ResponseBody
	public MessageObject userEditPassword(String email,String password,String verifyCode,HttpServletRequest req) {
		return this.tbUserService.updateUserPassword(email,password,verifyCode,req);
	}
}
