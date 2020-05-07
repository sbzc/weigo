package com.weigo.dubbo.order.service;

import java.util.List;

import com.weigo.pojo.TbOrderItem;
import com.weigo.pojo.TbOrderShipping;

public interface TbOrderDubboService {

	int insertOrderByOrderItemAndOrderShipping( List<TbOrderItem> tbOrderItems,
			List<TbOrderShipping> tbOrderShipping) throws Exception;

}
