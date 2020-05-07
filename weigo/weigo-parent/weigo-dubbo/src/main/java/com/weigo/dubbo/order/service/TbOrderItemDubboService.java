package com.weigo.dubbo.order.service;

import java.util.Date;
import java.util.List;

import com.weigo.pojo.TbOrderItem;
import com.weigo.pojo.TbOrderShipping;

public interface TbOrderItemDubboService {

	List<TbOrderItem> showOrderItemByclientId(Long id);

	TbOrderItem selectOredrItemByOrderItemId(String id);

	int updateOrderItemByTbOrderItem(TbOrderItem tbOrderItem);

	List<TbOrderItem> selectOrderItemBySellerId(Long id);

	int updateLogisticsByTbOrderItemAndOrderShipping(TbOrderItem tbOrderItem, TbOrderShipping tbOrderShipping) throws Exception;

	Long selectPendingClientOrderItemByClientId(Long id);

	Long selectPendingSellerOrderItemBySellerId(Long id);

	Long selectOredrItemCountByStatus(Date startdate,Date endDate, Integer integer);


}
