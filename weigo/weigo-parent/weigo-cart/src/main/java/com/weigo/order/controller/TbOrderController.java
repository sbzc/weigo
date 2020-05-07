package com.weigo.order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.order.pojo.MyOrderParam;
import com.weigo.order.service.TbOrderService;
import com.weigo.pojo.TbUser;

@Controller
public class TbOrderController {
	@Autowired
	private TbOrderService tbOrderService;
	@RequestMapping("/order/order-cart.html")
	public String  showOrder(@RequestParam(value = "id",defaultValue = "")List<Long> ids,Model model,HttpServletRequest req) {
	   if(ids.size()!=0) {
		Map<String, List<?>> map = tbOrderService.getOrder(ids,req);
		model.addAttribute("cartList", map.get("oderItemList"));
		model.addAttribute("addressList", map.get("addressList"));
	   }
		return "order-cart";
	}
	@RequestMapping("/order/create.html")
	public String insertOrder(Model model,MyOrderParam param,HttpServletRequest req) {
		MessageObject mo = new MessageObject();
		mo=tbOrderService.insertOrder(param,req);
		if(mo.getCode()==1) {
			TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
			model.addAttribute("payment",param.getPayment());
			model.addAttribute("orderId", mo.getMsg());
			model.addAttribute("phone", principal.getPhone());
			return "success";
		}else {
			model.addAttribute("messag",mo.getMsg());
			return "error/exception";
		}
	}
}
