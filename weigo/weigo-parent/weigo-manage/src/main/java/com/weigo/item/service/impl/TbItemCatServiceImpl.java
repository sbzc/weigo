package com.weigo.item.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.commons.pojo.EasyUiTree;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.utils.IDUtils;
import com.weigo.dubbo.item.service.TbItemCatDubboService;
import com.weigo.item.service.TbItemCatService;
import com.weigo.pojo.TbItemCat;
@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Autowired
	private TbItemCatDubboService tbItemCatDubboService;
	@Override
	public List<EasyUiTree> getItemCatByPid(long pid) {
		List<TbItemCat> list= tbItemCatDubboService.selectItemCatByPid(pid);
		List<EasyUiTree> listTree = new ArrayList<EasyUiTree>();
		for (TbItemCat cat : list) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(cat.getId());
			tree.setText(cat.getName());
			tree.setState(cat.getIsParent()?"closed":"open");
			listTree.add(tree);
		}
		return listTree;
	}
	@Override
	public MessageObject insertContentCategory(TbItemCat tbItemCat) {
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		List<TbItemCat> list =  tbItemCatDubboService.selectItemCatByPid(tbItemCat.getParentId());
		for (TbItemCat c : list) {
			if(c.getName().equals(tbItemCat.getName())) {
				return mo;
			}
		}
		Date date = new Date();
		long id = IDUtils.genItemId();
		tbItemCat.setId(id);
		tbItemCat.setCreated(date);
		tbItemCat.setUpdated(date);
		tbItemCat.setStatus(1);
		tbItemCat.setSortOrder(1);
		tbItemCat.setIsParent(false);
	    int rows=tbItemCatDubboService.insertTbItemCatByTbItemCat(tbItemCat);
		if(rows==1) {
			
			TbItemCat parent = new TbItemCat();
			parent.setId(tbItemCat.getParentId());
			parent.setIsParent(true);
			tbItemCatDubboService.updateTbItemCatByObj(parent);
			
			mo.setCode(1);
			
		}
		return mo;
	}
	@Override
	public MessageObject deleteContentCategory(TbItemCat tbItemCat) {
		MessageObject mo = new MessageObject();
		Date date = new Date();
		tbItemCat.setUpdated(date);
		tbItemCat.setStatus(0);
		mo.setMsg("删除失败");
		List<TbItemCat> selectItemCatByPid = tbItemCatDubboService.selectItemCatByPid(tbItemCat.getId());
		if(selectItemCatByPid!=null&&selectItemCatByPid.size()>0) {
			mo.setMsg("此分类还有子分类！！");
		 return mo;
		}
		
		TbItemCat itemCat = tbItemCatDubboService.selectCatNameById(tbItemCat.getId());
		int rows = tbItemCatDubboService.deleteCatById(tbItemCat.getId());
		if(rows>0) {
			List<TbItemCat> list = tbItemCatDubboService.selectItemCatByPid(itemCat.getParentId());		
			if(list.size()==0) {
				TbItemCat parent = new TbItemCat();
				parent.setId(itemCat.getParentId());
				parent.setUpdated(date);
				parent.setIsParent(false);
				tbItemCatDubboService.updateTbItemCatByObj(parent);
			}
		   mo.setCode(1);
		}
		return mo;
	}
	@Override
	public MessageObject updateContentCategory(TbItemCat TbItemCat) {
		MessageObject mo = new MessageObject();
		TbItemCat itemCat = tbItemCatDubboService.selectCatNameById(TbItemCat.getId());
		List<TbItemCat> list = tbItemCatDubboService.selectItemCatByPid(itemCat.getParentId());
		for (TbItemCat c : list) {
			if(c.getName().equals(TbItemCat.getName())) {
				mo.setCode(0);
				return mo;
			}
		}
		Date date = new Date();
		TbItemCat.setUpdated(date);
		int rows = tbItemCatDubboService.updateTbItemCatByObj(TbItemCat);
		if(rows==1) {
			mo.setCode(1);
		}
		return mo;
	}

}
