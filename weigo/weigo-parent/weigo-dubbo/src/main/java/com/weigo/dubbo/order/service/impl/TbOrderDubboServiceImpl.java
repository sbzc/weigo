package com.weigo.dubbo.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.authentication.MysqlOldPasswordPlugin;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.utils.IDUtils;
import com.weigo.dubbo.order.service.TbOrderDubboService;
import com.weigo.mapper.TbItemMapper;
import com.weigo.mapper.TbOrderItemMapper;
import com.weigo.mapper.TbOrderShippingMapper;
import com.weigo.mapper.TbUserAddressMapper;
import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbOrderItem;
import com.weigo.pojo.TbOrderShipping;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbUserAddress;
@Service
public class TbOrderDubboServiceImpl implements TbOrderDubboService {

	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;
	@Autowired
	private TbUserAddressMapper tbUserAddressMapper;
	@Autowired
	private TbItemMapper tbItemMapper;
	@Override
	public int insertOrderByOrderItemAndOrderShipping( List<TbOrderItem> tbOrderItems,
			List<TbOrderShipping> tbOrderShippings) throws Exception {
		int row=0;
		try {
			for (TbOrderItem tbOrderItem : tbOrderItems) {
				row+=tbOrderItemMapper.insertSelective(tbOrderItem);
			}
			for (TbOrderShipping tbOrderShipping : tbOrderShippings) {
				row-=tbOrderShippingMapper.insertSelective(tbOrderShipping);
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(row==0) {
			return 1;
		}else {
			throw new Exception("创建订单失败");
		}
		
	}
	//endId->param.getEndId();
//	public Map<String,Object> insertOrder(Long uid,Long endId,List<TbOrderItem> tbOrderItems){
//		 Map<String,Object> map = new HashMap<String, Object>();
//		MessageObject messageObject = new MessageObject();
//		Date date = new Date();
//		long orderId = IDUtils.genItemId();
//		//地址
//		TbUserAddress addressByEndId = tbUserAddressMapper.selectByPrimaryKey(endId);
//		//订单商品表
//		List<TbItem> tbItemObjList = new ArrayList<TbItem>();
//		//List<TbOrderItem> tbOrderItems = param.getOrderItems();
//		List<TbOrderShipping> tbOrderShippings = new ArrayList<TbOrderShipping>();
//		for (TbOrderItem tbOrderItem : tbOrderItems) {
//			TbItem tbItem = tbItemMapper.selectByPrimaryKey(tbOrderItem.getItemId());
//			if(tbItem==null) {
//				messageObject.setCode(2);
//				messageObject.setMsg("所选商品已经被删除");
//				map.put("messageObject", messageObject);
//			    return map;
//			}
//			//保存查出来的商品
//			tbItemObjList.add(tbItem);
//			//无货不能购买
//			if(tbItem.getNum()<tbOrderItem.getNum()) {
//				messageObject.setCode(2);
//				messageObject.setMsg("所选商品已经无货");
//				map.put("messageObject", messageObject);
//			    return map;
//			}
//			long orderItemId = IDUtils.genItemId();
//			tbOrderItem.setId(orderItemId+"");
//			tbOrderItem.setOrderId(orderId);
//			tbOrderItem.setCreated(date);
//			tbOrderItem.setUpdated(date);
//			tbOrderItem.setBuyerRate(0);
//			tbOrderItem.setStatus(0);
//			//uid=principal.getId()
//			tbOrderItem.setClientId(uid);
//			Long sellerId = tbUserDubboService.selectUserItemByItemId(tbOrderItem.getItemId()).getUid();
//			
//			
//			//如果是自己发布的商品部不能购买
//			if(sellerId.longValue()==principal.getId().longValue()) {
//				messageObject.setCode(2);
//				messageObject.setMsg(tbOrderItem.getTitle()+":是你发布的!!!");
//			    return messageObject;
//			}
//			tbOrderItem.setSellerId(sellerId);
//			tbOrderItem.setSellerIsread(0);
//			tbOrderItem.setClientIsread(0);
//			//添加对应的地址
//			TbOrderShipping tbOrderShipping = new TbOrderShipping();
//			tbOrderShipping.setOrderItemId(orderItemId+"");
//			tbOrderShipping.setEndAddress(addressByEndId.getAddressname());
//			tbOrderShipping.setEndPhone(addressByEndId.getPhone());
//			tbOrderShipping.setEndName(addressByEndId.getUsername());
//			
//			//快递员为空
//			tbOrderShipping.setUid(null);
//			tbOrderShipping.setUpdated(date);
//			tbOrderShipping.setCreated(date);
//			//设置该物流卖家未公开
//			tbOrderShipping.setStatus(0);
//			tbOrderShippings.add(tbOrderShipping);
//		}
//		
//		String ids="";
//		try {
//			
//			Date date2 = new Date();
//			
//			for(int i=0;i<tbOrderItems.size();i++) {
//				Integer num = tbOrderItems.get(i).getNum();
//				TbItem tbItem = tbItemObjList.get(i);
//				if((tbItem.getNum()-num)<=0) {
//					ids+=tbItem.getId()+",";
//				}
//				tbItem.setNum(tbItem.getNum()-num);
//				tbItem.setUpdated(date2);
//				this.tbItemDubboService.updateItemByTbItemObj(tbItem);
//			}
//			
//			int ok = tbOrderDubboService.insertOrderByOrderItemAndOrderShipping(tbOrderItems, tbOrderShippings);
//		
//		
//		
//		return null;
//	}

}
