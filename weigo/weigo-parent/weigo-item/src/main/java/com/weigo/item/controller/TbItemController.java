package com.weigo.item.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.item.service.TbItemDescService;
import com.weigo.item.service.TbItemService;
import com.weigo.pojo.TbItem;

@Controller
public class TbItemController {
	@Autowired
   private TbItemService tbItemService;
	@Autowired
	private TbItemDescService tbItemDescService;
	@RequestMapping("/item/{id}.html")
	public String showTbItemDescPage(Model model,@PathVariable Long id) {
		TbItem item = tbItemService.selectItemById(id);
		model.addAttribute("item",item);
		return "item";
	}
	@RequestMapping(value = "item/desc/{id}.html",produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String getItemDescById(@PathVariable Long id) {
		return tbItemDescService.getItemDescById(id);
	}
	
}
