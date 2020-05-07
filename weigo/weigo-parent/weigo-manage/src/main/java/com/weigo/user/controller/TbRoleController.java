package com.weigo.user.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbRole;
import com.weigo.user.service.TbRoleService;


@Controller
@RequestMapping("/role")
public class TbRoleController {
   @Autowired
	private TbRoleService tbRoleService;
	
	
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<TbRole> list(@RequestParam(defaultValue = "")String keyword,
			@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "10")int pageSize){
		 SecurityUtils.getSubject().checkPermission("role");
		 
		 
		return tbRoleService.getPageInfo(keyword,pageNum,pageSize);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public MessageObject delete(Long roleId) {
		 SecurityUtils.getSubject().checkPermission("role");
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		mo.setMsg("ɾ��ʧ��");
		int row = tbRoleService.deleteTbRoleById(roleId);
		
		if(row==1) {
					mo.setCode(1);
					mo.setMsg("ɾ���ɹ�");
		}else if(row==2){
					mo.setCode(2);
					mo.setMsg("ɾ��ʧ��,�����û�ӵ�д˽�ɫ");
		}
		
		return mo;
		
	}
	@RequestMapping("roleEdit")
	public String roleEdit(Model model,Long roleId) {
		 SecurityUtils.getSubject().checkPermission("role");
		if(roleId!=null) {
			TbRole role = tbRoleService.selectTbRoleById(roleId);
			model.addAttribute("role", role);
		}
		return "roleEdit";
	}
	@ResponseBody
	@RequestMapping("/insert")
	public MessageObject insert(TbRole role) {
		 SecurityUtils.getSubject().checkPermission("role");
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		mo.setMsg("����ʧ��");
	    
		int row = tbRoleService.insertTbRoleBySelective(role);
		if(row==1) {
			mo.setCode(1);
			mo.setMsg("���ӳɹ�");
		}
		return mo;
	}
   @ResponseBody
   @RequestMapping("/update")
   public MessageObject update(TbRole role) {
	   SecurityUtils.getSubject().checkPermission("role");
	   MessageObject mo = new MessageObject();
		mo.setCode(0);
		mo.setMsg("�޸�ʧ��");
	   
	   int row = tbRoleService.updeteTbRoleBySelective(role);
	   
	   
	   if(row==1) {
			mo.setCode(1);
			mo.setMsg("�޸ĳɹ�");
	   }
	   return mo;
   }
   
   
}
