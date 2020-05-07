package com.weigo.cart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbItem;

public interface CartService {

	MessageObject insertCart(long id, int num);

	List<TbItem> showCart();

	MessageObject updateCart(long id, int num, HttpServletRequest req);

	MessageObject deleteCartById(String ids);

}
