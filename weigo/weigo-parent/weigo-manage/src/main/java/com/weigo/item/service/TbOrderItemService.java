package com.weigo.item.service;

import java.util.Date;
import java.util.List;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.VisitorPage;
import com.weigo.pojo.TbOrderItem;

public interface TbOrderItemService {

	List<TbOrderItem> showOrderItemByclientId();

	MessageObject updateOrderItemStatusByTbOrderItem(TbOrderItem tbOrderItem);

	List<TbOrderItem> selectSellerOrderItemListBySellerId();

	List<VisitorPage> selectItemChartMessage(Date date);

}
