package com.weigo.job.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.commons.pojo.ConstantObject;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.ShippingMessage;
import com.weigo.dubbo.order.service.TbOrderItemDubboService;
import com.weigo.dubbo.shipping.service.TbOrderShippingDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.job.service.DispatcherService;
import com.weigo.mail.email.SendEmail;
import com.weigo.pojo.TbOrderItem;
import com.weigo.pojo.TbOrderShipping;
import com.weigo.pojo.TbUser;
@Service
public class DispatcherServiceImpl implements DispatcherService {

	@Autowired
	private TbOrderShippingDubboService tbOrderShippingService;
	@Autowired
	private TbOrderItemDubboService tbOrderItemDubboService;
	@Autowired
	private TbUserDubboService tbUserDubboService;
	@Override
	public List<ShippingMessage> selectShippingMessageByAll() {
		List<ShippingMessage> ShippingMessageList= new ArrayList<ShippingMessage>();
		List<TbOrderShipping>TbOrderShippingList = tbOrderShippingService.selectOrderShippingByStatus(ConstantObject.prepareSendOrderItem);
		for (TbOrderShipping tbOrderShipping : TbOrderShippingList) {
			ShippingMessage shippingMessage = new ShippingMessage();
			//id
			shippingMessage.setOrderItemId(tbOrderShipping.getOrderItemId());
			//创建时间
			shippingMessage.setUpdated(tbOrderShipping.getUpdated());
			//发货人姓名
			shippingMessage.setStartName(tbOrderShipping.getStartName());
			//发货人地址
			shippingMessage.setStartAddress(tbOrderShipping.getStartAddress());
			//发货人电话
			shippingMessage.setStartPhone(tbOrderShipping.getStartPhone());
			//收货人姓名
			shippingMessage.setEndName(tbOrderShipping.getEndName());
			//收货人地址
			shippingMessage.setEndAddress(tbOrderShipping.getEndAddress());
			//收货人电话
			shippingMessage.setEndPhone(tbOrderShipping.getEndPhone());
			TbOrderItem tbOrderItem = tbOrderItemDubboService.selectOredrItemByOrderItemId(tbOrderShipping.getOrderItemId());
			//商品标题
			shippingMessage.setItemTitle(tbOrderItem.getTitle());
			//图片地址
			shippingMessage.setImage(tbOrderItem.getPicPath());
			//商品id
			shippingMessage.setItemId(tbOrderItem.getItemId());
			//商品总价格
			shippingMessage.setPrivce(tbOrderItem.getTotalFee());
			ShippingMessageList.add(shippingMessage);
		}
		
		return ShippingMessageList;
	}
	@Override
	public MessageObject dispatcherTakeOrders(String orderItemId) {
		MessageObject mo = new MessageObject();
		TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
		Long uid = principal.getId();
		int res=0 ;
		try {
			res= tbOrderShippingService.updateShippingAndOrderItem(orderItemId,uid);
		} catch (Exception e) {
			mo.setCode(0);
			mo.setMsg("系统出错了");
		}
	
		if(res==0) {
			mo.setCode(0);
			mo.setMsg("下手过慢,单被接走了！！");
		}else {
			mo.setCode(1);
			mo.setMsg("接单成功");
			TbOrderItem orderItem = this.tbOrderItemDubboService.selectOredrItemByOrderItemId(orderItemId);
			
			
			Map<String,String> clientMap = new HashMap<String, String>();
			TbUser client = this.tbUserDubboService.selectUserById(orderItem.getClientId());
			clientMap.put("mail", client.getEmail());
			clientMap.put("name", client.getUsername());
			clientMap.put("title", orderItem.getTitle());
			clientMap.put("totalFee",String.valueOf(orderItem.getTotalFee()/100));
			clientMap.put("dispatcherName", principal.getUsername());
			clientMap.put("dispatcherPhone", principal.getPhone());
			
			Map<String,String> sellerMap = new HashMap<String, String>();
			TbUser seller = this.tbUserDubboService.selectUserById(orderItem.getSellerId());
			sellerMap.put("mail", seller.getEmail());
			sellerMap.put("name", seller.getUsername());
			sellerMap.put("title", orderItem.getTitle());
			sellerMap.put("totalFee",String.valueOf(orderItem.getTotalFee()/100));
			sellerMap.put("num",String.valueOf(orderItem.getNum()));
			
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
							//卖家发通知
							String s = "你的商品："+clientMap.get("title")+"&nbsp;X"+sellerMap.get("num")+"<br>:待快递员取货。。。。<br>"+"总价格："+sellerMap.get("totalFee");
							SendEmail.send_email(sellerMap.get("mail"),sellerMap.get("name")+":快递小哥接单了", s);
						
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				};
			}.start();
			
		}
		return mo;
	}

}
