package com.weigo.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.item.service.TbItemDescDubboService;
import com.weigo.item.service.TbItemDescService;
import com.weigo.pojo.TbItemDesc;
@Service
public class TbItemDescServiceImpl implements TbItemDescService {
 
	@Autowired
	private TbItemDescDubboService tbItemDescDubboService;
	@Override
	public String getItemDescById(Long id) {
		TbItemDesc itemDesc = tbItemDescDubboService.selectTbItemDescById(id);
		if(itemDesc!=null) {
			return itemDesc.getItemDesc();
		}
		return null;
	}

}
