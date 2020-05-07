package com.weigo.item.controller;



import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.item.service.TbItemService;
import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbUser;

@Controller
public class TbItemController {
   @Autowired
	private TbItemService tbItemService;
 
   @RequestMapping("/user/item/list")
   @ResponseBody
   public PageInfo<TbItem> showUserItem(String keyword,@RequestParam(defaultValue = "updated")String sort,@RequestParam(defaultValue = "desc")String sortOrder,
		   @RequestParam(defaultValue = "10") int pageSize,@RequestParam(defaultValue = "1" )int pageNum){
	   SecurityUtils.getSubject().checkPermission("seller");
	   TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
	      PageInfo<TbItem> info = this.tbItemService.selectItemByUid(keyword,pageSize,pageNum,sort,sortOrder,user.getUsername());
	   return info;
   }
   @RequestMapping("/admin/item/list")
   @ResponseBody
   public PageInfo<TbItem> showAllUserItem(String keyword,@RequestParam(defaultValue = "updated")String sort,@RequestParam(defaultValue = "desc")String sortOrder,
		   @RequestParam(defaultValue = "10") int pageSize,@RequestParam(defaultValue = "1" )int pageNum,@RequestParam(defaultValue = "")String username){
	   SecurityUtils.getSubject().checkPermission("item");  
	   PageInfo<TbItem> info = this.tbItemService.selectItemByUid(keyword,pageSize,pageNum,sort,sortOrder,username);
	   return info;
   }
   @RequestMapping("/item/delete")
   @ResponseBody
   public MessageObject deleteItemByIds(String id) {
	   MessageObject mo = new MessageObject();
	   mo.setCode(0);
	   mo.setMsg("删除失败");
       
	   int row = this.tbItemService.deleteItemByIds(id);
	   if(row==1) {
		   mo.setCode(1);
		   mo.setMsg("删除成功");
	   }
	  return mo;
   }
   @RequestMapping("/item/update")
   @ResponseBody
   public MessageObject updateItemStatus(String ids,int status) {
	   MessageObject mo = new MessageObject();
	   mo.setCode(0);
	   if(status==1) {
		   mo.setMsg("上架失败");
	   }else {
		   mo.setMsg("下架失败");
	   }
	  int row = tbItemService.updateItemByTbItem(ids,status);
	  if(row==1) {
		  mo.setCode(1);
		  if(status==1) {
			   mo.setMsg("上架成功");
		   }else {
			   mo.setMsg("下架成功");
		   }
	  }
	   return mo;
   }
   @RequestMapping("/item/save")
   @ResponseBody
   public MessageObject saveItem(TbItem item,String desc) {
	   
	   SecurityUtils.getSubject().checkPermission("/item/save");
	   
	   MessageObject mo = new MessageObject();
	   mo.setCode(0);
	  mo.setMsg("添加商品失败");
	   int row=this.tbItemService.insertItemByItemAndDesc(item,desc);
	   if(row==1) {
		   mo.setCode(1);
		   mo.setMsg("添加商品成功");
	   }
	   return mo;
   }
   @RequestMapping("/item/itemEdit")
   public String itemEdit(@RequestParam(required=true) long id,Model model){
	   TbItem item=this.tbItemService.selectItemById(id);
	   model.addAttribute("item", item);
	   return "itemEdit";
   }
   @RequestMapping("/rest/item/update")
   @ResponseBody
   public MessageObject restUpdateItem(TbItem item) {
	  MessageObject mo = new MessageObject();
	  mo.setCode(0);
	  mo.setMsg("修改失败");
	  int row = tbItemService.restUpdateItem(item);
	  if(row==1) {
		  mo.setCode(1);
		  mo.setMsg("修改成功");
	  }
	  return mo;
   }
   
  
   
   
}
