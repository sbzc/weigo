package com.weigo.order.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.order.pojo.MyOrderParam;


public interface TbOrderService {

	Map<String, List<?>> getOrder(List<Long> ids, HttpServletRequest req);

	MessageObject insertOrder(MyOrderParam param, HttpServletRequest req);

}
