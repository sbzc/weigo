package com.weigo.job.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.ConstantObject;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.ShippingMessage;
import com.weigo.job.service.DispatcherService;
import com.weigo.portal.service.TbVisitorService;

@Controller
public class DispatcherController {

	@Autowired
	private DispatcherService dispatcherService;
	@Autowired
	private TbVisitorService tbVisitorService;
	
	@RequestMapping("/dispatcher/show/list")
	public String showDispatcherList(Model model) {
		
		new Thread(()->{
			tbVisitorService.insertVisitor(ConstantObject.dispatcherPage);
		}).start();
		
		SecurityUtils.getSubject().checkPermission("/dispatcher/show/list");
		List<ShippingMessage> list = dispatcherService.selectShippingMessageByAll();
		model.addAttribute("shippingMessages", list);
		return "showDispatcher";
	}
	@RequestMapping("/dispatcher/takeOrders")
	@ResponseBody
	public MessageObject dispatcherTakeOrders(String orderItemId) {
		return dispatcherService.dispatcherTakeOrders(orderItemId);
	}
}
