package com.weigo.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.utils.HttpClientUtil;
import com.weigo.commons.utils.IDUtils;
import com.weigo.commons.utils.JsonUtils;
import com.weigo.dubbo.item.service.TbItemDubboService;
import com.weigo.dubbo.order.service.TbOrderDubboService;
import com.weigo.dubbo.user.service.TbAddressDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.mail.email.SendEmail;
import com.weigo.order.pojo.MyOrderParam;
import com.weigo.order.service.TbOrderService;
import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbOrderItem;
import com.weigo.pojo.TbOrderShipping;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbUserAddress;
@Service
public class TbOrderServiceImpl implements TbOrderService {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private TbItemDubboService tbItemDubboService;
	@Value("${redis.cart.key}")
	private String cartKey;
	@Autowired
	private TbOrderDubboService tbOrderDubboService;
	@Autowired
	private TbAddressDubboService tbAddressDubboService;
	@Autowired
	private TbUserDubboService tbUserDubboService;
	@Override
	public Map<String,List<?>> getOrder(List<Long> ids, HttpServletRequest req) {
		
		this.redisTemplate.setValueSerializer(new StringRedisSerializer());
		TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
		String name=principal.getUsername();
		String key = cartKey+name;
		List<TbItem> oderItemList = new ArrayList<TbItem>();
		if(redisTemplate.hasKey(key)) {
			String json = redisTemplate.opsForValue().get(key);
			List<TbItem> allItem = JsonUtils.jsonToList(json, TbItem.class);
			for (TbItem tbItemc : allItem) {
				
				for (Long id : ids) {
				
					if(tbItemc.getId().longValue()==id.longValue()) {
						TbItem tbItem = tbItemDubboService.selectTbItemById(id);
						if(tbItem==null) {
							tbItemc.setEnough(false);
						}else {
							Integer num = tbItemc.getNum();
							if(tbItem.getNum()>=num&&(int)tbItem.getStatus()==1) {
								tbItemc.setEnough(true);
							}else {
								tbItemc.setEnough(false);
							}
						}
						
						oderItemList.add(tbItemc);
						break;
						}
					
					
				}
			}
		}
		Map<String, List<?>> map = new HashMap<String, List<?>>();
		List<TbUserAddress> addressList =  tbAddressDubboService.selectTbUserAddressByUid(principal.getId());
		map.put("oderItemList",oderItemList);
		map.put("addressList",addressList);
		return map;
	}
	@Override
	public MessageObject insertOrder(MyOrderParam param, HttpServletRequest req) {
		this.redisTemplate.setValueSerializer(new StringRedisSerializer());
		MessageObject messageObject = new MessageObject();
		TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
		String name=principal.getUsername();
		String key = cartKey+name;
		Date date = new Date();
		long orderId = IDUtils.genItemId();
		//��ַ
		TbUserAddress addressByEndId = tbAddressDubboService.selectTbUserAddressById(param.getEndId());
		//������Ʒ��
		List<TbItem> tbItemObjList = new ArrayList<TbItem>();
		List<TbOrderItem> tbOrderItems = param.getOrderItems();
		List<TbOrderShipping> tbOrderShippings = new ArrayList<TbOrderShipping>();
		for (TbOrderItem tbOrderItem : tbOrderItems) {
			TbItem tbItem = tbItemDubboService.selectTbItemById(tbOrderItem.getItemId());
			if(tbItem==null) {
				messageObject.setCode(2);
				messageObject.setMsg("��ѡ��Ʒ�Ѿ���ɾ��");
			    return messageObject;
			}
			//������������Ʒ
			tbItemObjList.add(tbItem);
			//�޻����ܹ���
			if(tbItem.getNum()<tbOrderItem.getNum()) {
				messageObject.setCode(2);
				messageObject.setMsg("��ѡ��Ʒ�Ѿ��޻�");
			    return messageObject;
			}
			long orderItemId = IDUtils.genItemId();
			tbOrderItem.setId(orderItemId+"");
			tbOrderItem.setOrderId(orderId);
			tbOrderItem.setCreated(date);
			tbOrderItem.setUpdated(date);
			tbOrderItem.setBuyerRate(0);
			tbOrderItem.setStatus(0);
			tbOrderItem.setClientId(principal.getId());
			Long sellerId = tbUserDubboService.selectUserItemByItemId(tbOrderItem.getItemId()).getUid();
			
			
			//������Լ���������Ʒ�����ܹ���
			if(sellerId.longValue()==principal.getId().longValue()) {
				messageObject.setCode(2);
				messageObject.setMsg(tbOrderItem.getTitle()+":���㷢����!!!");
			    return messageObject;
			}
			tbOrderItem.setSellerId(sellerId);
			tbOrderItem.setSellerIsread(0);
			tbOrderItem.setClientIsread(0);
			//���Ӷ�Ӧ�ĵ�ַ
			TbOrderShipping tbOrderShipping = new TbOrderShipping();
			tbOrderShipping.setOrderItemId(orderItemId+"");
			tbOrderShipping.setEndAddress(addressByEndId.getAddressname());
			tbOrderShipping.setEndPhone(addressByEndId.getPhone());
			tbOrderShipping.setEndName(addressByEndId.getUsername());
			
			//���ԱΪ��
			tbOrderShipping.setUid(null);
			tbOrderShipping.setUpdated(date);
			tbOrderShipping.setCreated(date);
			//���ø���������δ����
			tbOrderShipping.setStatus(0);
			tbOrderShippings.add(tbOrderShipping);
		}
		
		String ids="";
		try {
			//������Ʒ
			Date date2 = new Date();
			for(int i=0;i<tbOrderItems.size();i++) {
				Integer num = tbOrderItems.get(i).getNum();
				TbItem tbItem = tbItemObjList.get(i);
				if((tbItem.getNum()-num)<=0) {
					ids+=tbItem.getId()+",";
				}
				tbItem.setNum(tbItem.getNum()-num);
				tbItem.setUpdated(date2);
				this.tbItemDubboService.updateItemByTbItemObj(tbItem);
			}
			//��������
			int ok = tbOrderDubboService.insertOrderByOrderItemAndOrderShipping(tbOrderItems, tbOrderShippings);
			if(ok==1) {
				List<Map<String,String>> mailList = new ArrayList<Map<String,String>>();
				
				for (TbOrderItem tbOrderItem : tbOrderItems) {
					Map<String,String> map = new HashMap<String, String>();
				       TbUser selectUserById = this.tbUserDubboService.selectUserById(tbOrderItem.getSellerId());
				      map.put("mail",selectUserById.getEmail());
				      map.put("name", selectUserById.getUsername());
				      map.put("title",tbOrderItem.getTitle());
				      mailList.add(map);
				}
				
				
				new Thread() {
					public void run() {
						for (Map<String, String> map : mailList) {
							try {
								SendEmail.send_email(map.get("mail"),map.get("name")+":�����Ʒ�пͻ��µ���", "��Ʒ����"+map.get("title"));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
						
					//	
					};
				}.start();
				
				
				//�����˵���ƷӦ���¼�
				if(!ids.equals("")) {
					String substring = ids.substring(0,ids.length()-1);
					this.tbItemDubboService.updateItemByTbItem(substring, 0);
					new Thread() {
						public void run() {
							  HttpClientUtil.doPost("http://47.103.218.192:8083/solr/delete?ids="+substring);
						};
					}.start();
					
				}
				
				
				messageObject.setCode(1);
				messageObject.setMsg(orderId+"");
				//�������ﳵ
				String json = redisTemplate.opsForValue().get(key);
				List<TbItem> cartList = JsonUtils.jsonToList(json, TbItem.class);
				List<TbItem> removeCarts = new ArrayList<TbItem>();
				for (TbItem tbItemc : cartList) {
					for (TbOrderItem tbOrderItem : tbOrderItems) {
						if(tbItemc.getId().longValue()==tbOrderItem.getItemId().longValue()) {
							removeCarts.add(tbItemc);
						}
					}
				}
				for (TbItem tbItemc : removeCarts) {
					cartList.remove(tbItemc);
				}
				//ɾ��
				redisTemplate.opsForValue().set(key, JsonUtils.objectToJson(cartList));
				
			}else {
				messageObject.setCode(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			messageObject.setCode(0);
			messageObject.setMsg("ϵͳ����");
		}
		
		return messageObject;
	}

}