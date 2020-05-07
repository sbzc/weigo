package com.weigo.logistics.service;

import java.util.List;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbOrderShipping;

public interface LogisticsService {

	MessageObject updateLogisticsByStartIdAndOrderItemId(Long startId, String orderItemId);

	List<TbOrderShipping> selectOrderShippingByUid();

	MessageObject updateLogisticsByOrderItemId(String orderItemId);

	MessageObject dispatcherReset(String orderItemId);

	MessageObject dispatcherSingNo(String orderItemId);

}
