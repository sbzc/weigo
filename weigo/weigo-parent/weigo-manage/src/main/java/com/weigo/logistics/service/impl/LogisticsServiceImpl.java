package com.weigo.logistics.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.commons.pojo.ConstantObject;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.dubbo.order.service.TbOrderItemDubboService;
import com.weigo.dubbo.shipping.service.TbOrderShippingDubboService;
import com.weigo.dubbo.user.service.TbAddressDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.logistics.service.LogisticsService;
import com.weigo.mail.email.SendEmail;
import com.weigo.pojo.TbOrderItem;
import com.weigo.pojo.TbOrderShipping;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbUserAddress;
@Service
public class LogisticsServiceImpl implements LogisticsService {

	@Autowired
	private TbOrderItemDubboService tbOrderItemDubboService;
	@Autowired
	private TbAddressDubboService tbAddressDubboService;
	@Autowired
	private TbOrderShippingDubboService TbOrderShippingDubboService;
	@Autowired
	private TbUserDubboService tbUserDubboService;
	@Override
	public MessageObject updateLogisticsByStartIdAndOrderItemId(Long startId, String orderItemId) {
		TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		Date date = new Date();
		TbOrderItem tbOrderItem = tbOrderItemDubboService.selectOredrItemByOrderItemId(orderItemId);
		tbOrderItem.setUpdated(date);
		if(tbOrderItem.getStatus()!=ConstantObject.resetOrderItem) {
			mo.setMsg("该订单状态不是待发货状态,招募失败！！");
			return mo;
		}
		if(tbOrderItem.getSellerId().longValue()!=user.getId().longValue()) {
			mo.setMsg("你没有操作该订单的权限");
			return mo;
		}
		tbOrderItem.setStatus(ConstantObject.prepareSendOrderItem);
		TbOrderShipping tbOrderShipping = new TbOrderShipping();
		tbOrderShipping.setOrderItemId(orderItemId);
		tbOrderShipping.setUpdated(date);
		TbUserAddress userAddress = tbAddressDubboService.selectTbUserAddressById(startId);
		tbOrderShipping.setStartAddress(userAddress.getAddressname());
		tbOrderShipping.setStartName(userAddress.getUsername());
		tbOrderShipping.setStartPhone(userAddress.getPhone());
		tbOrderShipping.setStatus(ConstantObject.prepareSendOrderItem);
		int ok=0;
	 try {
			ok =tbOrderItemDubboService.updateLogisticsByTbOrderItemAndOrderShipping(tbOrderItem,tbOrderShipping);
		} catch (Exception e) {
			mo.setMsg("系统故障！！");
			
			return mo;
		}
		if(ok==1) {
			mo.setCode(1);
			mo.setMsg("发布招募成功");
		}else {
			mo.setMsg("发布招募失败");
		}
		
		return mo;
	}
	@Override
	public List<TbOrderShipping> selectOrderShippingByUid() {
		TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		
		List<TbOrderShipping> list = TbOrderShippingDubboService.selectOrderShippingByUid(user.getId());
		for (TbOrderShipping tbOrderShipping : list) {
			TbOrderItem orderItem = tbOrderItemDubboService.selectOredrItemByOrderItemId(tbOrderShipping.getOrderItemId());
			tbOrderShipping.setItemId(orderItem.getItemId());
			tbOrderShipping.setImage(orderItem.getPicPath());
			tbOrderShipping.setPrice(orderItem.getPrice());
			tbOrderShipping.setItemTitle(orderItem.getTitle());
			tbOrderShipping.setNum(orderItem.getNum());
		}
		
		return list;
	}
	@Override
	public MessageObject updateLogisticsByOrderItemId(String orderItemId) {
		TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		Date date = new Date();
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		
		TbOrderItem tbOrderItem = this.tbOrderItemDubboService.selectOredrItemByOrderItemId(orderItemId);
		
		
		
		if(tbOrderItem==null) {
			mo.setMsg("该订单已经被删除");
		}else if(tbOrderItem.getSellerId().longValue()!=user.getId().longValue()) {
			mo.setMsg("你没有操作该订单的权限");
		}else {
			//招募快递员或者是代发货状态都可以转为自己配送
			if(tbOrderItem.getStatus()==ConstantObject.prepareSendOrderItem||tbOrderItem.getStatus()==ConstantObject.resetOrderItem) {
				
				TbOrderShipping tbOrderShipping = new TbOrderShipping();
				tbOrderShipping.setOrderItemId(orderItemId);
				tbOrderShipping.setUid(user.getId());
				tbOrderShipping.setUpdated(date);
				tbOrderShipping.setStatus(ConstantObject.SendOrderItem);
				
				tbOrderItem.setUpdated(date);
				tbOrderItem.setStatus(ConstantObject.SendOrderItem);
				
				try {
					 int ok = tbOrderItemDubboService.updateLogisticsByTbOrderItemAndOrderShipping(tbOrderItem,tbOrderShipping);
					 if(ok==1) {
						 mo.setCode(1);
						 mo.setMsg("操作成功");
						 TbOrderItem orderItem = this.tbOrderItemDubboService.selectOredrItemByOrderItemId(orderItemId);
						 Map<String,String> clientMap = new HashMap<String, String>();
							TbUser client = this.tbUserDubboService.selectUserById(orderItem.getClientId());
							clientMap.put("mail", client.getEmail());
							clientMap.put("name", client.getUsername());
							clientMap.put("title", orderItem.getTitle());
							clientMap.put("totalFee",String.valueOf(orderItem.getTotalFee()/100));
							clientMap.put("dispatcherName", user.getUsername());
							clientMap.put("dispatcherPhone", user.getPhone());
							new Thread() {
								public void run() {
										try {
											StringBuilder dispatcherMessage = new StringBuilder();
											dispatcherMessage.append("<h5>快递员信息</h5>电话：");
											dispatcherMessage.append(clientMap.get("dispatcherPhone")+"<br>");
											dispatcherMessage.append("姓名"+clientMap.get("dispatcherName")+"<br>");
											dispatcherMessage.append("货到应付："+clientMap.get("totalFee"));
											//买家发通知
											String d = "你购买的商品："+clientMap.get("title")+"<br>:商品正在配送中。。。。<br>"+dispatcherMessage.toString();
											SendEmail.send_email(clientMap.get("mail"),clientMap.get("name")+":快递小哥接单了", d);
										
										
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} 
								};	
							}.start();
					 }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}else {
				mo.setMsg("订单状态有误");
			}
		}
		
		return mo;
	}
	
	//快递员退单
	@Override
	public MessageObject dispatcherReset(String orderItemId) {
		TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		Date date = new Date();
		
		TbOrderShipping tbOrderShipping= TbOrderShippingDubboService.selectOrderShippingByShippingId(orderItemId);
		if(tbOrderShipping.getUid().longValue()!=user.getId().longValue()) {
			mo.setMsg("你没有操作该订单的权限");
		}else if(tbOrderShipping.getStatus()==ConstantObject.SendOrderItem) {
			
			 tbOrderShipping.setUpdated(date);
			 tbOrderShipping.setUid(0l);
			 tbOrderShipping.setStatus(ConstantObject.prepareSendOrderItem);
			 
			 TbOrderItem tbOrderItem = new TbOrderItem();
			 tbOrderItem.setId(orderItemId);
			 tbOrderItem.setUpdated(date);
			 tbOrderItem.setStatus(ConstantObject.prepareSendOrderItem);
			try {
				int ok = tbOrderItemDubboService.updateLogisticsByTbOrderItemAndOrderShipping(tbOrderItem,tbOrderShipping);
				if(ok==1) {
					mo.setCode(1);
					mo.setMsg("退单成功");
					
					TbOrderItem orderItem = this.tbOrderItemDubboService.selectOredrItemByOrderItemId(orderItemId);
					Map<String,String> map = new HashMap<String, String>();
					//给卖家发信息
					TbUser selectUserById = this.tbUserDubboService.selectUserById(orderItem.getSellerId());
					map.put("mail", selectUserById.getEmail());
					map.put("name", selectUserById.getUsername());
					map.put("title", orderItem.getTitle());
					new Thread() {
						public void run() {
								try {
									SendEmail.send_email(map.get("mail"),map.get("name")+":快递员退单", "<b>退单商品：<b>"+map.get("title")+"请尽快处理");
								} catch (Exception e) {
									e.printStackTrace();
								} 
						};
					}.start();
					
					
				}else {
					mo.setCode(0);
					mo.setMsg("退单失败");
				}
			} catch (Exception e) {
				mo.setMsg("退单失败");
				e.printStackTrace();
			}
			
		}
 		
		return mo;
	}
    //签收
	@Override
	public MessageObject dispatcherSingNo(String orderItemId) {
		TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		Date date = new Date();
		
		TbOrderShipping tbOrderShipping= TbOrderShippingDubboService.selectOrderShippingByShippingId(orderItemId);
		if(tbOrderShipping.getUid().longValue()!=user.getId().longValue()) {
			mo.setMsg("你没有操作该订单的权限");
		}else if(tbOrderShipping.getStatus()==ConstantObject.SendOrderItem) {
			
			 tbOrderShipping.setUpdated(date);
			// tbOrderShipping.setUid(0l);
			 tbOrderShipping.setStatus(ConstantObject.signOnOrderItem);
			 
			 TbOrderItem tbOrderItem = new TbOrderItem();
			 tbOrderItem.setId(orderItemId);
			 tbOrderItem.setUpdated(date);
			 tbOrderItem.setStatus(ConstantObject.signOnOrderItem);
			try {
				int ok = tbOrderItemDubboService.updateLogisticsByTbOrderItemAndOrderShipping(tbOrderItem,tbOrderShipping);
				if(ok==1) {
					mo.setCode(1);
					mo.setMsg("签收成功");
					
				}else {
					mo.setCode(0);
					mo.setMsg("签收失败");
				}
			} catch (Exception e) {
				mo.setMsg("签收失败");
				e.printStackTrace();
			}
			
		}
 		
		return mo;
	}
	
}
