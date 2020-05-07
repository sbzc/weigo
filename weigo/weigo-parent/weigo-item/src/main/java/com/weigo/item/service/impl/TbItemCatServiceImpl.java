package com.weigo.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.item.service.TbItemCatDubboService;
import com.weigo.item.pojo.ItemCatMenu;
import com.weigo.item.pojo.ItemCatMenuNode;
import com.weigo.item.service.TbItemCatService;
import com.weigo.pojo.TbItemCat;
@Service
public class TbItemCatServiceImpl implements TbItemCatService {
    @Autowired
	private TbItemCatDubboService tbItemCatDubboService;
	
	@Override
	public ItemCatMenu selectItemCatMenu() {
		List<TbItemCat> catByPid = tbItemCatDubboService.selectItemCatByPid(0);
	    ItemCatMenu itemCatMenu = new ItemCatMenu();
	    itemCatMenu.setData(selectAllMenuByTbitemCats(catByPid));
		return itemCatMenu;
	}

	private List<Object> selectAllMenuByTbitemCats(List<TbItemCat> list) {
		List<Object> listNode = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			if(tbItemCat.getIsParent()){
				ItemCatMenuNode pmd  = new ItemCatMenuNode();
				pmd.setU("/products/"+tbItemCat.getId()+".html");
				pmd.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				pmd.setI(selectAllMenuByTbitemCats(tbItemCatDubboService.selectItemCatByPid(tbItemCat.getId())));
				listNode.add(pmd);
			}else{
				listNode.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		
		return listNode;
	}
	
	

}
