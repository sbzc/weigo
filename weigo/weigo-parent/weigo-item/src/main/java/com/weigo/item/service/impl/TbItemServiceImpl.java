package com.weigo.item.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.weigo.dubbo.item.service.TbItemDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.item.service.TbItemService;
import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbUserItem;
@Service
public class TbItemServiceImpl implements TbItemService {
     @Autowired
	private TbItemDubboService tbItemDubboService;
     @Autowired
     private TbUserDubboService tbUserDubboService;
	@Override
	public TbItem selectItemById(Long id) {
		TbItem tbItem = tbItemDubboService.selectTbItemById(id);
		System.out.println(tbItem);
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

}
