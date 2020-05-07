package com.weigo.job.service;

import java.util.List;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.ShippingMessage;

public interface DispatcherService {

	List<ShippingMessage> selectShippingMessageByAll();

	MessageObject dispatcherTakeOrders(String orderItemId);

}
