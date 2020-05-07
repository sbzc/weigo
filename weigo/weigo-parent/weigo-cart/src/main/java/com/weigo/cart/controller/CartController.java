package com.weigo.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.cart.service.CartService;
import com.weigo.cart.service.TbVisitorService;
import com.weigo.commons.pojo.ConstantObject;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbItem;



@Controller
public class CartController {
   @Autowired
	private CartService cartService;
   @Autowired
   private TbVisitorService tbVisitorService;
	@RequestMapping("/cart/add/{id}.action")
	@ResponseBody
	public MappingJacksonValue insertCart(@PathVariable long id,int num,String callback) {
		MessageObject mo = cartService.insertCart(id,num);
		MappingJacksonValue mjv = new MappingJacksonValue(mo);
		mjv.setJsonpFunction(callback);
		return mjv;
	}
	@RequestMapping("/cart/cart.html")
	public String showCart(Model model,HttpServletRequest req) {
		new Thread(()->{
			tbVisitorService.insertVisitor(ConstantObject.cartPage);
		}).start();
		List<TbItem> list =  cartService.showCart();
		model.addAttribute("cartList", list);
		return "cart";
	}
	//jsonp·½Ê½
	@RequestMapping("/cart/showCart.action")
	@ResponseBody
	public MappingJacksonValue showCartByJsonp(String callback) {
		new Thread(()->{
			tbVisitorService.insertVisitor(ConstantObject.cartPage);
		}).start();
		List<TbItem> list =  cartService.showCart();
		MappingJacksonValue mjv = new MappingJacksonValue(list);
		mjv.setJsonpFunction(callback);
		return mjv;
	}
	
	@RequestMapping("/cart/update/num/{id}/{num}.action")
	@ResponseBody
	public MessageObject updateCart(@PathVariable long id, @PathVariable int num,HttpServletRequest req) {
		MessageObject mo = new MessageObject();
		mo = cartService.updateCart(id,num,req);
		return mo;
	}
	@RequestMapping("/cart/delete/{ids}.action")
	@ResponseBody
	public Object deleteCart(@PathVariable String ids,String callback) {
		MessageObject mo = new MessageObject();
		mo=cartService.deleteCartById(ids);
		if(callback==null) {
			//mo.setStatus(0);
			return mo;
		}else {
			MappingJacksonValue mjv = new MappingJacksonValue(mo);		
			mjv.setJsonpFunction(callback);
			return mjv;
		}
	}
}
