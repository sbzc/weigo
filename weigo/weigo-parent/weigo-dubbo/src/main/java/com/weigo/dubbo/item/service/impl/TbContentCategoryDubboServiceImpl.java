package com.weigo.dubbo.item.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.item.service.TbContentCategoryDubboService;
import com.weigo.mapper.TbContentCategoryMapper;
import com.weigo.pojo.TbContentCategory;
import com.weigo.pojo.TbContentCategoryExample;
@Service
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<TbContentCategory> getContentCategory(Long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
		
		
		return tbContentCategoryMapper.selectByExample(example );
	}

	

	@Override
	public int insertContentCategory(TbContentCategory category) {
		
		return tbContentCategoryMapper.insertSelective(category);
	}

	@Override
	public int updIsParentById(TbContentCategory parent) {
		
		return tbContentCategoryMapper.updateByPrimaryKeySelective(parent);
	}

	@Override
	public TbContentCategory selectContentCategoryById(Long id) {
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}
}
