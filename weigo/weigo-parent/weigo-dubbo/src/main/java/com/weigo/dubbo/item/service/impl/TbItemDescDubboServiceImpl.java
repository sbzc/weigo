package com.weigo.dubbo.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.item.service.TbItemDescDubboService;
import com.weigo.mapper.TbItemDescMapper;
import com.weigo.pojo.TbItemDesc;
@Service
public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public TbItemDesc selectTbItemDescById(long id) {
		return tbItemDescMapper.selectByPrimaryKey(id);
	}

}
