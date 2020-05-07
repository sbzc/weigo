package com.weigo.visitor.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.VisitorPage;
import com.weigo.item.service.TbItemService;
import com.weigo.item.service.TbOrderItemService;
import com.weigo.pojo.TbUserLogin;
import com.weigo.user.service.TbRoleService;
import com.weigo.user.service.TbUserService;
import com.weigo.visitor.service.TbUserLoginService;
import com.weigo.visitor.service.TbVisitorService;
/*
 * 用于管理员查看访客情况
 */
@Controller
public class AdminVisitorController {
	@Autowired
	private TbUserLoginService tbUserLoginService;
	@Autowired
	private TbVisitorService tbVisitorService;
	 @Autowired
	private TbRoleService tbRoleService;
	 @Autowired
	private TbOrderItemService tbOrderItemService;
	 @Autowired
	 private TbItemService tbItemService;
	 @Autowired
	 private TbUserService tbUserService;
	 
	//登录数据表
	   @RequestMapping("/admin/visitor/userlogin/list")
	   @ResponseBody
	   public PageInfo<TbUserLogin> showAllTbUserLogin(String keyword,@RequestParam(defaultValue = "login_datetime")String sort,@RequestParam(defaultValue = "desc")String sortOrder,
			   @RequestParam(defaultValue = "10") int pageSize,@RequestParam(defaultValue = "1" )int pageNum,String startTime,String endTime,@RequestParam(defaultValue = "")String username){
		   SecurityUtils.getSubject().checkPermission("/admin/visitor/userlogin/list");
		   
		   DateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-dd");
		  
		  Date startDate=null;
		  Date endDate=null;
		  
		  try {
			startDate=dateFormat.parse(startTime);
		} catch (Exception e1) {
		}
		  try {
			  endDate=dateFormat.parse(endTime);
		    } catch (Exception e) {
		}
		  return tbUserLoginService.getUserLoginList(keyword,pageSize,pageNum,sort,sortOrder,startDate,endDate,username);
	   } 
	   //删除登录信息表
	@RequestMapping("/admin/visitor/userlogin/delete")
	@ResponseBody
	public MessageObject deleteUserLogin(String ids) {
		return tbUserLoginService.deleteUserLoginByIds(ids);
	}
	//获取页面访客信息
     @RequestMapping("/admin/visitor/page")
     @ResponseBody
     public List<VisitorPage> selectVisitorConunt(String qDate){
    	 SecurityUtils.getSubject().checkPermission("/admin/visitor/page");
    	 
    	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	 Date date = new Date();
    	 try {
    		 date =dateFormat.parse(qDate);
		} catch (Exception e) {
		}
    	 
    	 return tbVisitorService.selectVisitorConunt(date);
     }
     //登录折线图信息
     @RequestMapping("/admin/visitor/userlogin/count")
     @ResponseBody
     public VisitorPage selectUserLoginCount(String qDate) {
    	 SecurityUtils.getSubject().checkPermission("/admin/visitor/page");
    	  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	      	 Date date = new Date();
	      	 try {
	      		 date =dateFormat.parse(qDate);
	  		} catch (Exception e) {
	  		}
    	 
    	 return tbUserLoginService.selectVisitorPageByDate(date);
     }
     //角色分布信息
     @RequestMapping("/role/chartMessage")
     @ResponseBody
     public VisitorPage selectRoleChartMessage() {
    	 SecurityUtils.getSubject().checkPermission("/role/chartMessage");
  	   return tbRoleService.selectRoleChartMessage();
     }
     //订单信息
       @RequestMapping("/item/chartMessage")
	   @ResponseBody
	   public List<VisitorPage> selectItemChartMessage(String qDate){
    	 SecurityUtils.getSubject().checkPermission("/item/chartMessage");
    	 
    	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	 Date date = new Date();
    	 try {
    		 date =dateFormat.parse(qDate);
		} catch (Exception e) {
		}
		   return tbOrderItemService.selectItemChartMessage(date);
	   }
     //获取增加商品的趋势
       @RequestMapping("/item/add/chartMessage")
       @ResponseBody
       public VisitorPage selectItemAddChartMessage(String qDate) {
    	   
    	   SecurityUtils.getSubject().checkPermission("/item/add/chartMessage");
    	   
    	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	      	 Date date = new Date();
	      	 try {
	      		 date =dateFormat.parse(qDate);
	  		} catch (Exception e) {
	  		}
	      	 return tbItemService.selectItemAddCharMessageByDate(date);
       }
       @RequestMapping("/user/charMessage")
       @ResponseBody
       public VisitorPage selectUserChartMessage(String qDate) {
    	   
    	   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	      	 Date date = new Date();
	      	 try {
	      		 date =dateFormat.parse(qDate);
	  		} catch (Exception e) {
	  		}
	      	 return tbUserService.selectUserChartMessage(date);
       }
}
