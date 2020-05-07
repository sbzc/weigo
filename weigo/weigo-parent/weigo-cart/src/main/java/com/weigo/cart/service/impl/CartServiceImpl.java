package com.weigo.cart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.weigo.cart.service.CartService;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.utils.JsonUtils;
import com.weigo.dubbo.item.service.TbItemDubboService;
import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbUser;
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Value("${redis.cart.key}")
	private String cartKey;
	@Autowired
	private TbItemDubboService tbItemDubboservice;
	
	@Override
	public MessageObject insertCart(long id, int num) {
		
		
		this.redisTemplate.setValueSerializer(new StringRedisSerializer());
		
		MessageObject mo = new MessageObject();
		
		List<TbItem> list = new ArrayList<TbItem>();
		TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
		String name=principal.getUsername();
		String key = cartKey+name;
		if(redisTemplate.hasKey(key)) {
			String string = redisTemplate.opsForValue().get(key);
			list = JsonUtils.jsonToList(string, TbItem.class);
			for (TbItem tb : list) {
				if((Long)tb.getId()==id) {
					tb.setNum(tb.getNum()+num);
					redisTemplate.opsForValue().set(key, JsonUtils.objectToJson(list));
					mo.setMsg(tb.getImages()[0]);
					mo.setCode(1);
					return mo;
				}
			}
		}
			TbItem item = tbItemDubboservice.selectTbItemById(id);
			item.setNum(num);
			if(item.getSellPoint()==null) {
				item.setSellPoint("什么也没留下");
			}
			item.setImages(item.getImage()==null||item.getImage().equals("")?new String[1]:item.getImage().split(","));
			list.add(item);
			redisTemplate.opsForValue().set(key, JsonUtils.objectToJson(list));
			redisTemplate.expire(key, 3, TimeUnit.DAYS);
			mo.setMsg(item.getImages()[0]);
			mo.setCode(1);
		return mo;
	}
	public List<TbItem> showCart() {
		this.redisTemplate.setValueSerializer(new StringRedisSerializer());
		TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
		String name = principal.getUsername();
		String key = cartKey+name;
		String json = redisTemplate.opsForValue().get(key);
		return JsonUtils.jsonToList(json, TbItem.class);
	}
	@Override
	public MessageObject updateCart(long id, int num, HttpServletRequest req) {
		this.redisTemplate.setValueSerializer(new StringRedisSerializer());
		TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
		String name = principal.getUsername();
		String key = cartKey+name;
		String tbItemSearchStr = redisTemplate.opsForValue().get(key);
		List<TbItem> list = JsonUtils.jsonToList(tbItemSearchStr, TbItem.class);
		for (TbItem tb : list) {
			if((Long)tb.getId()==id) {
				tb.setNum(num);
                String value = JsonUtils.objectToJson(list);
                redisTemplate.opsForValue().set(key, value);
				MessageObject moc = new MessageObject();
				moc.setCode(1);
				return moc;
			}
		}
		
		return null;
	}
	@Override
	public MessageObject deleteCartById(String ids) {
		String[] split = ids.split(",");
		this.redisTemplate.setValueSerializer(new StringRedisSerializer());
		TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
		String name = principal.getUsername();
		String key = cartKey+name;
		String tbItemSearchStr = redisTemplate.opsForValue().get(key);
		List<TbItem> list = JsonUtils.jsonToList(tbItemSearchStr, TbItem.class);
		List<TbItem> removObj = new ArrayList<TbItem>();
		for (TbItem tb : list) {
			for (String id : split) {
				if((long)tb.getId()==Long.parseLong(id)) {
					removObj.add(tb);
				}
			}
		}
		for (TbItem tbItem : removObj) {
			list.remove(tbItem);
		}
		 redisTemplate.opsForValue().set(key, JsonUtils.objectToJson(list));
		MessageObject mo = new MessageObject();
			mo.setCode(1);
			mo.setMsg("删除成功");
		return mo;
	}

}