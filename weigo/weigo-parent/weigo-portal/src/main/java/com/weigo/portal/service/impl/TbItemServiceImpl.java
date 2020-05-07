package com.weigo.portal.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.item.service.TbItemDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbUserItem;
import com.weigo.portal.service.TbItemService;
@Service
public class TbItemServiceImpl implements TbItemService {

	@Autowired
	private TbItemDubboService tbItemDubboService;
	@Autowired
	private TbUserDubboService tbUserDubboService;
	@Override
	public TbItem selectItemByItemId(Long id) {
		TbItem tbItem = tbItemDubboService.selectTbItemById(id);
		tbItem.setImages(tbItem.getImage()!=null&&!"".equals(tbItem.getImage())?tbItem.getImage().split(","):new String[1]);
		
		TbUserItem tbUserItem = tbUserDubboService.selectUserItemByItemId(id);
		if(tbUserItem!=null) {
			TbUser tbUser = tbUserDubboService.selectUserByUserId(tbUserItem.getUid());
			if(tbUser!=null) {
				tbItem.setRoleId(tbUser.getRoleId()>5?5+"":tbUser.getRoleId().toString());
				tbItem.setUsername(tbUser.getUsername());
			}
		}
		return tbItem;
	}
	@Override
	public Map<String, Object> selectItemAndEqItemByItemId(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		TbItem tbItem = tbItemDubboService.selectTbItemById(id);
		tbItem.setImages(tbItem.getImage()!=null&&!"".equals(tbItem.getImage())?tbItem.getImage().split(","):new String[1]);
		TbUserItem tbUserItem = tbUserDubboService.selectUserItemByItemId(id);
		if(tbUserItem!=null) {
			TbUser tbUser = tbUserDubboService.selectUserByUserId(tbUserItem.getUid());
			if(tbUser!=null) {
				tbItem.setRoleId(tbUser.getRoleId()>5?5+"":tbUser.getRoleId().toString());
				tbItem.setUsername(tbUser.getUsername());
			}
		}
		map.put("item", tbItem);
		List<TbItem> items = tbItemDubboService.selectItemByCatIdAndCount(tbItem.getCid(),16);
		if(items.size()<16) {
			List<TbItem> list = tbItemDubboService.selectItemByCount(16-items.size());
			items.addAll(list);
		}
            for (TbItem item : items) {
			
			if(item.getImage()!=null) {
				item.setImage(item.getImage().split(",")[0]);
			}
			
			
			TbUserItem userItem = tbUserDubboService.selectUserItemByItemId(item.getId());
			if(userItem!=null) {
				TbUser tbUser = tbUserDubboService.selectUserByUserId(userItem.getUid());
				if(tbUser!=null)
					item.setUsername(tbUser.getUsername());
			}
		}
		
		map.put("likeness",items.subList(0, 8));
		map.put("recommend",items.subList(8, 16));
		return map;
	}

}
