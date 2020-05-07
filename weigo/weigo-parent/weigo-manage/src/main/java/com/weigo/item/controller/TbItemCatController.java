package com.weigo.item.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.weigo.commons.pojo.EasyUiTree;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.item.service.TbItemCatService;
import com.weigo.pojo.TbItemCat;
@Controller
public class TbItemCatController {
	@Autowired
	private TbItemCatService tbItemCatService;
    @RequestMapping("/item/cat/list")
    @ResponseBody
	public List<EasyUiTree> showItemCat(@RequestParam(defaultValue = "0") long id) {
	   return tbItemCatService.getItemCatByPid(id);
	 
   }
    @RequestMapping("/item/cat/insert")
	@ResponseBody
	public MessageObject insertContentCategory(TbItemCat tbItemCat) {
    	SecurityUtils.getSubject().checkPermission("page");
		return tbItemCatService.insertContentCategory(tbItemCat);
	}
	@RequestMapping("/item/cat/delete")
	@ResponseBody
	public MessageObject deleteContentCategory(TbItemCat tbItemCat) {
		SecurityUtils.getSubject().checkPermission("page");
		return tbItemCatService.deleteContentCategory(tbItemCat);
	}
	@RequestMapping("/item/cat/update")
	@ResponseBody
	public MessageObject updateContentCategory(TbItemCat tbItemCat) {
		SecurityUtils.getSubject().checkPermission("page");
		return tbItemCatService.updateContentCategory(tbItemCat);
	}
    
}
