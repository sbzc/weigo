package com.weigo.dubbo.item.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.item.service.TbItemCatDubboService;
import com.weigo.mapper.TbItemCatMapper;
import com.weigo.pojo.TbItemCat;
import com.weigo.pojo.TbItemCatExample;
@Service
public class TbItemCatDubboServiceImpl implements TbItemCatDubboService {
    @Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<TbItemCat> selectItemCatByPid(long pid) {
		TbItemCatExample example = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(pid).andStatusEqualTo(1);
		return tbItemCatMapper.selectByExample(example);
	}
	@Override
	public TbItemCat selectCatNameById(Long cid) {
		return tbItemCatMapper.selectByPrimaryKey(cid);
	}
	@Override
	public int insertTbItemCatByTbItemCat(TbItemCat tbItemCat) {
		return tbItemCatMapper.insert(tbItemCat);
	}
	@Override
	public int updateTbItemCatByObj(TbItemCat parent) {
		
		return tbItemCatMapper.updateByPrimaryKeySelective(parent);
	}
	@Override
	public int deleteCatById(Long id) {
		return tbItemCatMapper.deleteByPrimaryKey(id);
	}

}
