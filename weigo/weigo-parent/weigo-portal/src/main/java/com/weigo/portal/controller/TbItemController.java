package com.weigo.portal.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.ConstantObject;
import com.weigo.pojo.TbItem;
import com.weigo.portal.pojo.PortalMessage;
import com.weigo.portal.service.TbItemCatService;
import com.weigo.portal.service.TbItemDescService;
import com.weigo.portal.service.TbItemService;
import com.weigo.portal.service.TbVisitorService;


@Controller
public class TbItemController {
	@Autowired
	private TbItemService tbItemService;
	@Autowired
	private TbItemCatService tbItemCatService;
	@Autowired
	private TbItemDescService tbItemDescService;
	@Autowired
	private TbVisitorService tbVisitorService;
 	@RequestMapping("/portal/index")
	public String portalIndex(Model model) {
 		new Thread(()-> {
 		 tbVisitorService.insertVisitor(ConstantObject.PortalPage);
 	  }).start();;
		PortalMessage portalMessage= tbItemCatService.selectPortalMessag();
		model.addAttribute("portalMessage", portalMessage);
		return "index";
	}
	@RequestMapping("/item/showProductMin/{id}.html")
	public String showItemProductMin(@PathVariable Long id,Model model) {
		TbItem tbItem=tbItemService.selectItemByItemId(id);
		model.addAttribute("item", tbItem);
		return "productMin";
		
	}
	
	@RequestMapping("/item/showProductMain/{id}.html")
	public String showItemProductMain(@PathVariable Long id,Model model) {
		Map<String,Object> map=tbItemService.selectItemAndEqItemByItemId(id);
		model.addAttribute("item", map.get("item"));
		model.addAttribute("likeness", map.get("likeness"));
		model.addAttribute("recommend", map.get("recommend"));
		return "productMain";
	}
	@RequestMapping(value="/item/desc/{itemId}.html",produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String getItemDescByItemId(@PathVariable Long itemId) {
		new Thread(()-> {
	 		 tbVisitorService.insertVisitor(ConstantObject.itemdescPage);
	 	  }).start();
		return tbItemDescService.getItemDescByItemId(itemId);
	} 
	
}
