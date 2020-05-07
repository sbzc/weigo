package com.weigo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.item.service.TbOrderItemService;
import com.weigo.pojo.TbOrderItem;
@Controller
public class TbUserOrderItemController {
	
	@Autowired
	private TbOrderItemService tbOrderItemService;
	
	 @RequestMapping("/user/buyitem/list")
	 public String showUserItemList(Model model) {
		 List<TbOrderItem> orderItems = tbOrderItemService.showOrderItemByclientId();
		 model.addAttribute("orderItems", orderItems);
		 return "userBuyOrder";
	 }
	 @RequestMapping("/user/orderItem/update")
	 @ResponseBody
	 public MessageObject updateOrderItemStatus(TbOrderItem tbOrderItem) {
		 return tbOrderItemService.updateOrderItemStatusByTbOrderItem(tbOrderItem);
	 }
	 @RequestMapping("/seller/OrderItem/list")
		public String showSellerOrderItemList(Model model) {
			List<TbOrderItem> orderItems = tbOrderItemService.selectSellerOrderItemListBySellerId();
			 model.addAttribute("orderItems", orderItems);
			return "sellerOrder";
		}
}
