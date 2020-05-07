package com.weigo.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.item.pojo.ItemCatMenu;
import com.weigo.item.service.TbItemCatService;

@Controller
public class TbItemCatController {
	
	@Autowired
	private TbItemCatService tbItemCatService;
	
	
	@RequestMapping("/rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue showItemMenu(String callback) {
		ItemCatMenu itemCarMenu = tbItemCatService.selectItemCatMenu();
	     MappingJacksonValue jacksonValue = new MappingJacksonValue(itemCarMenu);
	     jacksonValue.setJsonpFunction(callback);
	     return jacksonValue;
	}

}
