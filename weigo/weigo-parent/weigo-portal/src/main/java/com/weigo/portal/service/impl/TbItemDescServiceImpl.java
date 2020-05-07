package com.weigo.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.item.service.TbItemDescDubboService;
import com.weigo.pojo.TbItemDesc;
import com.weigo.portal.service.TbItemDescService;
@Service
public class TbItemDescServiceImpl implements TbItemDescService {

	@Autowired
	private TbItemDescDubboService  tbItemDescDubboService;
	
	@Override
	public String getItemDescByItemId(Long itemId) {
		TbItemDesc selectTbItemDescById = tbItemDescDubboService.selectTbItemDescById(itemId);
		
		return selectTbItemDescById.getItemDesc();
	}

}
