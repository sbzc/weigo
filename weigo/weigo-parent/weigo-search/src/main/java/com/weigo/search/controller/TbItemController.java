package com.weigo.search.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weigo.commons.pojo.ConstantObject;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbItem;
import com.weigo.search.service.TbItemService;
import com.weigo.search.service.TbVisitorService;

@Controller
public class TbItemController {
     @Autowired
	private TbItemService tbItemService;
     @Autowired
     private TbVisitorService tbVisitorService;
	@RequestMapping("/solr/insertItems")
	@ResponseBody
	public int insertItems(String ids) {
		System.out.println("TbItemController.insertItems()");
		int ok=0;
		try {
			ok=tbItemService.insertItemsByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("TbItemController.insertItems()ok="+ok);
		return ok;
	}
	
     @RequestMapping("/solr/insert")
 	@ResponseBody
 	public int insert(@RequestBody TbItem tbItem) {
 		int ok = 0;
 		ok=tbItemService.insertTbItemtoSolr(tbItem);
 		System.out.println("insertpk="+ok);
 		return ok;
 	}
     @RequestMapping("/solr/delete")
     @ResponseBody
     public int deleteItemByIds(String ids) {
    	 int ok=0;
    	try {
			ok= tbItemService.deleteItemByIds(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 return ok;
     }
     
	@RequestMapping(value="/sorl/init")
	@ResponseBody
	public MappingJacksonValue sorlItemInit(String callback) {
		MessageObject mo = new MessageObject();
		 long start = System.currentTimeMillis();
			try {
				tbItemService.solrItemInit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mo.setCode(0);
				mo.setMsg("初始化错误："+e.getMessage());
				 MappingJacksonValue mjv = new MappingJacksonValue(mo);
				 mjv.setJsonpFunction(callback);
				 
				  return mjv;
			}
			
		 long end = System.currentTimeMillis();
		 long time=(end-start)/1000;
		 mo.setCode(1);
		 mo.setMsg("初始化成功:用时->"+time+"秒");
		 MappingJacksonValue mjv = new MappingJacksonValue(mo);
		 mjv.setJsonpFunction(callback);
		  return mjv;
	}
	
	@RequestMapping("/search.html")
	public String search(Model model,@RequestParam(defaultValue = "")String q,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="6") int rows){
		new Thread(()->{
			tbVisitorService.insertVisitor(ConstantObject.searchPage);
		}).start();
		
		try {
			q = new String(q.getBytes("iso-8859-1"),"utf-8");
			Map<String, Object> map = tbItemService.selectItemByQuery(q, page, rows);
			model.addAttribute("query", q);
			model.addAttribute("itemList", map.get("itemList"));
			model.addAttribute("totalPages", map.get("totalPages"));
			model.addAttribute("page", page);
			model.addAttribute("count", map.get("count"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "search";
	}
	
}
