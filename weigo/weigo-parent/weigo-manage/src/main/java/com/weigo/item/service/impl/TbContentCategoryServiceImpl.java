package com.weigo.item.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.ZTreeObject;
import com.weigo.commons.utils.IDUtils;
import com.weigo.dubbo.item.service.TbContentCategoryDubboService;
import com.weigo.item.service.TbContentCategoryService;
import com.weigo.pojo.TbContentCategory;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
	private TbContentCategoryDubboService tbContentCategoryDubboService;
	
	@Override
	public List<ZTreeObject> getContentCategory(Long id) {
		List<ZTreeObject> trees = new ArrayList<ZTreeObject>();
		List<TbContentCategory> lists=tbContentCategoryDubboService.getContentCategory(id);
		
		for (TbContentCategory category : lists) {
			ZTreeObject zto = new ZTreeObject();
			zto.setId(category.getId());
			zto.setText(category.getName());
			zto.setState(category.getIsParent()?"closed":"open");
			trees.add(zto);
		}
		
		return trees;
	}

	@Override
	public MessageObject insertContentCategory(TbContentCategory category) {
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		List<TbContentCategory> list =  tbContentCategoryDubboService.getContentCategory(category.getParentId());
		for (TbContentCategory c : list) {
			if(c.getName().equals(category.getName())) {
				return mo;
			}
		}
		Date date = new Date();
		long id = IDUtils.genItemId();
		category.setId(id);
		category.setCreated(date);
		category.setUpdated(date);
		category.setStatus(1);
		category.setSortOrder(1);
	    category.setIsParent(false);
	    int rows=tbContentCategoryDubboService.insertContentCategory(category);
		if(rows==1) {
			
			TbContentCategory parent = new TbContentCategory();
			parent.setId(category.getParentId());
			parent.setIsParent(true);
			tbContentCategoryDubboService.updIsParentById(parent);
			
			mo.setCode(1);
			
		}
		return mo;
	}

	@Override
	public MessageObject deleteContentCategory(TbContentCategory category) {
		MessageObject mo = new MessageObject();
		Date date = new Date();
		category.setUpdated(date);
		category.setStatus(0);
		int rows = tbContentCategoryDubboService.updIsParentById(category);
		if(rows>0) {
			TbContentCategory tbContentCategory = tbContentCategoryDubboService.selectContentCategoryById(category.getId());
			List<TbContentCategory> list = tbContentCategoryDubboService.getContentCategory(tbContentCategory.getParentId());		
			if(list.size()==0) {
				TbContentCategory parent = new TbContentCategory();
				parent.setId(tbContentCategory.getParentId());
				parent.setUpdated(date);
				parent.setIsParent(false);
				 tbContentCategoryDubboService.updIsParentById(parent);
			}
		   mo.setCode(1);
		}
		return mo;
	}

	@Override
	public MessageObject updateContentCategory(TbContentCategory category) {
		MessageObject mo = new MessageObject();
		TbContentCategory tbContentCategory = tbContentCategoryDubboService.selectContentCategoryById(category.getId());
		List<TbContentCategory> list = tbContentCategoryDubboService.getContentCategory(tbContentCategory.getParentId());
		for (TbContentCategory c : list) {
			if(c.getName().equals(category.getName())) {
				mo.setCode(0);
				return mo;
			}
		}
		Date date = new Date();
		category.setUpdated(date);
		int rows = tbContentCategoryDubboService.updIsParentById(category);
		if(rows==1) {
			mo.setCode(1);
		}
		return mo;
	}

}
