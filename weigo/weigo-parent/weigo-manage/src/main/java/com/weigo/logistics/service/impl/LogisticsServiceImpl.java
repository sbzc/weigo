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
			mo.setMsg("�ö���״̬���Ǵ�����״̬,��ļʧ�ܣ���");
			return mo;
		}
		if(tbOrderItem.getSellerId().longValue()!=user.getId().longValue()) {
			mo.setMsg("��û�в����ö�����Ȩ��");
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
			mo.setMsg("ϵͳ���ϣ���");
			
			return mo;
		}
		if(ok==1) {
			mo.setCode(1);
			mo.setMsg("������ļ�ɹ�");
		}else {
			mo.setMsg("������ļʧ��");
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
			mo.setMsg("�ö����Ѿ���ɾ��");
		}else if(tbOrderItem.getSellerId().longValue()!=user.getId().longValue()) {
			mo.setMsg("��û�в����ö�����Ȩ��");
		}else {
			//��ļ���Ա�����Ǵ�����״̬������תΪ�Լ�����
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
						 mo.setMsg("�����ɹ�");
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
											dispatcherMessage.append("<h5>���Ա��Ϣ</h5>�绰��");
											dispatcherMessage.append(clientMap.get("dispatcherPhone")+"<br>");
											dispatcherMessage.append("����"+clientMap.get("dispatcherName")+"<br>");
											dispatcherMessage.append("����Ӧ����"+clientMap.get("totalFee"));
											//��ҷ�֪ͨ
											String d = "�㹺�����Ʒ��"+clientMap.get("title")+"<br>:��Ʒ���������С�������<br>"+dispatcherMessage.toString();
											SendEmail.send_email(clientMap.get("mail"),clientMap.get("name")+":���С��ӵ���", d);
										
										
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
				mo.setMsg("����״̬����");
			}
		}
		
		return mo;
	}
	
	//���Ա�˵�
	@Override
	public MessageObject dispatcherReset(String orderItemId) {
		TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		Date date = new Date();
		
		TbOrderShipping tbOrderShipping= TbOrderShippingDubboService.selectOrderShippingByShippingId(orderItemId);
		if(tbOrderShipping.getUid().longValue()!=user.getId().longValue()) {
			mo.setMsg("��û�в����ö�����Ȩ��");
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
					mo.setMsg("�˵��ɹ�");
					
					TbOrderItem orderItem = this.tbOrderItemDubboService.selectOredrItemByOrderItemId(orderItemId);
					Map<String,String> map = new HashMap<String, String>();
					//�����ҷ���Ϣ
					TbUser selectUserById = this.tbUserDubboService.selectUserById(orderItem.getSellerId());
					map.put("mail", selectUserById.getEmail());
					map.put("name", selectUserById.getUsername());
					map.put("title", orderItem.getTitle());
					new Thread() {
						public void run() {
								try {
									SendEmail.send_email(map.get("mail"),map.get("name")+":���Ա�˵�", "<b>�˵���Ʒ��<b>"+map.get("title")+"�뾡�촦��");
								} catch (Exception e) {
									e.printStackTrace();
								} 
						};
					}.start();
					
					
				}else {
					mo.setCode(0);
					mo.setMsg("�˵�ʧ��");
				}
			} catch (Exception e) {
				mo.setMsg("�˵�ʧ��");
				e.printStackTrace();
			}
			
		}
 		
		return mo;
	}
    //ǩ��
	@Override
	public MessageObject dispatcherSingNo(String orderItemId) {
		TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		Date date = new Date();
		
		TbOrderShipping tbOrderShipping= TbOrderShippingDubboService.selectOrderShippingByShippingId(orderItemId);
		if(tbOrderShipping.getUid().longValue()!=user.getId().longValue()) {
			mo.setMsg("��û�в����ö�����Ȩ��");
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
					mo.setMsg("ǩ�ճɹ�");
					
				}else {
					mo.setCode(0);
					mo.setMsg("ǩ��ʧ��");
				}
			} catch (Exception e) {
				mo.setMsg("ǩ��ʧ��");
				e.printStackTrace();
			}
			
		}
 		
		return mo;
	}
	
}
