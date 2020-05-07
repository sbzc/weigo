package com.weigo.dubbo.shipping.service;

import java.util.List;

import com.weigo.pojo.TbOrderShipping;

public interface TbOrderShippingDubboService {

	List<TbOrderShipping> selectOrderShippingByStatus(int status);

	int updateShippingAndOrderItem(String orderItemId, Long uid)throws Exception ;


	List<TbOrderShipping> selectOrderShippingByUid(Long id);

	TbOrderShipping selectOrderShippingByShippingId(String orderItemId);

	Long selectPendingOrderShippingByUid(Long id);

}
