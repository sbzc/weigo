package com.weigo.logistics.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.logistics.service.LogisticsService;
import com.weigo.pojo.TbOrderShipping;

@Controller
public class LogisticsController {

	@Autowired
	private LogisticsService logisticsService;

	@RequestMapping("/logistics/insertLogistics")
	@ResponseBody
	public MessageObject insertLogistics(Long startId,String orderItemId) {
		 SecurityUtils.getSubject().checkPermission("dispatcher");
		MessageObject mo = new MessageObject();
		mo =logisticsService.updateLogisticsByStartIdAndOrderItemId(startId,orderItemId);
		return mo;
	}
	
	@RequestMapping("/logistics/updateLogistics")
	@ResponseBody
	public MessageObject updateLogistics(String orderItemId) {
		 SecurityUtils.getSubject().checkPermission("dispatcher");
		MessageObject mo = new MessageObject();
		mo =logisticsService.updateLogisticsByOrderItemId(orderItemId);
		return mo;
	}
	@RequestMapping("/logistics/dispatcherOrder")
	public String showDispatcherOrder(Model model) {
		 SecurityUtils.getSubject().checkPermission("dispatcher");
		List<TbOrderShipping> tbOrderShippings = logisticsService.selectOrderShippingByUid();
		model.addAttribute("tbOrderShippings", tbOrderShippings);
		return "dispatcherOrder";
	}
	@RequestMapping("/logistics/dispatcherReset")
	@ResponseBody
	public MessageObject dispatcherReset(String orderItemId) {
		 SecurityUtils.getSubject().checkPermission("dispatcher");
		return logisticsService.dispatcherReset(orderItemId);
	}
	@RequestMapping("/logistics/dispatcherSingNo")
	@ResponseBody
	public MessageObject dispatcherSingNo(String orderItemId) {
		 SecurityUtils.getSubject().checkPermission("dispatcher");
		return logisticsService.dispatcherSingNo(orderItemId);
	}
}
