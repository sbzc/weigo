package com.weigo.user.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbPermission;
import com.weigo.user.service.TbPermissionService;

@RequestMapping("/permission")
@Controller
public class TbPermissionController {
	@Autowired
	private TbPermissionService permissionService; 
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<TbPermission> list(@RequestParam(defaultValue = "")String keyword,
			@RequestParam(defaultValue = "1")int pageNum,@RequestParam(defaultValue = "10")int pageSize){
		 SecurityUtils.getSubject().checkPermission("permission");
		return permissionService.getPageInfo(keyword,pageNum,pageSize);
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public MessageObject delete(Long permissionId) {
		 SecurityUtils.getSubject().checkPermission("permission");
		MessageObject mo =  new MessageObject();
		mo.setCode(0);
		mo.setMsg("删除失败");
		int row = permissionService.deleteTbPermissionById(permissionId);
		
		if(row==1) {
			mo.setCode(1);
			mo.setMsg("删除成功");
			
		}else if(row == 2) {
			 mo.setCode(2);
			 mo.setMsg("该权限有子权限");
		}
		
		
		return mo;
	}
	@RequestMapping("/permissionEdit")
	public String permissionEdit(Model model,Long permissionId) {
		 SecurityUtils.getSubject().checkPermission("permission");
		if(permissionId!=null) {
			TbPermission permission = permissionService.selectTbPermissionById(permissionId);
			model.addAttribute("permission", permission);
		}
		
		
		String type="menu";
		List<TbPermission> permissions = permissionService.selectTbPermissionByType(type);
		model.addAttribute("permissions", permissions);
		return "permissionEdit";
	}
	@ResponseBody
	@RequestMapping("/insert")
	public MessageObject insert(TbPermission permission) {
		 SecurityUtils.getSubject().checkPermission("permission");
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		mo.setMsg("添加失败");
		int ok = permissionService.insertBySelective(permission);
		
		if(ok==1) {
			mo.setCode(1);
			mo.setMsg("添加成功");
		}
		return mo;	
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public MessageObject update(TbPermission permission) {
		 SecurityUtils.getSubject().checkPermission("permission");
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		mo.setMsg("修改失败");
		
		int row = permissionService.updateBySelective(permission);
		if(row==1) {
			 mo.setCode(1);
			 mo.setMsg("修改成功");
		}
		return mo;
	}
	
	@ResponseBody
	@RequestMapping("/checkUsername")
	public boolean checkUsername(String name) {
	
		boolean ok = permissionService.selectTbPermissionByName(name);
		
		return ok;
	}
	@RequestMapping("/getPermissionAll")
	@ResponseBody
	public List<TbPermission> getPermissionAll() {
		 SecurityUtils.getSubject().checkPermission("permission");
		return permissionService.selectTbPermissionAll();
		
	}
}
