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
			//����ʱ��
			shippingMessage.setUpdated(tbOrderShipping.getUpdated());
			//����������
			shippingMessage.setStartName(tbOrderShipping.getStartName());
			//�����˵�ַ
			shippingMessage.setStartAddress(tbOrderShipping.getStartAddress());
			//�����˵绰
			shippingMessage.setStartPhone(tbOrderShipping.getStartPhone());
			//�ջ�������
			shippingMessage.setEndName(tbOrderShipping.getEndName());
			//�ջ��˵�ַ
			shippingMessage.setEndAddress(tbOrderShipping.getEndAddress());
			//�ջ��˵绰
			shippingMessage.setEndPhone(tbOrderShipping.getEndPhone());
			TbOrderItem tbOrderItem = tbOrderItemDubboService.selectOredrItemByOrderItemId(tbOrderShipping.getOrderItemId());
			//��Ʒ����
			shippingMessage.setItemTitle(tbOrderItem.getTitle());
			//ͼƬ��ַ
			shippingMessage.setImage(tbOrderItem.getPicPath());
			//��Ʒid
			shippingMessage.setItemId(tbOrderItem.getItemId());
			//��Ʒ�ܼ۸�
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
			mo.setMsg("ϵͳ������");
		}
	
		if(res==0) {
			mo.setCode(0);
			mo.setMsg("���ֹ���,���������ˣ���");
		}else {
			mo.setCode(1);
			mo.setMsg("�ӵ��ɹ�");
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
							dispatcherMessage.append("<h5>���Ա��Ϣ</h5>�绰��");
							dispatcherMessage.append(clientMap.get("dispatcherPhone")+"<br>");
							dispatcherMessage.append("����"+clientMap.get("dispatcherName")+"<br>");
							dispatcherMessage.append("����Ӧ����"+clientMap.get("totalFee"));
							//��ҷ�֪ͨ
							String d = "�㹺�����Ʒ��"+clientMap.get("title")+"<br>:��Ʒ���������С�������<br>"+dispatcherMessage.toString();
							SendEmail.send_email(clientMap.get("mail"),clientMap.get("name")+":���С��ӵ���", d);
							//���ҷ�֪ͨ
							String s = "�����Ʒ��"+clientMap.get("title")+"&nbsp;X"+sellerMap.get("num")+"<br>:�����Աȡ����������<br>"+"�ܼ۸�"+sellerMap.get("totalFee");
							SendEmail.send_email(sellerMap.get("mail"),sellerMap.get("name")+":���С��ӵ���", s);
						
						
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
