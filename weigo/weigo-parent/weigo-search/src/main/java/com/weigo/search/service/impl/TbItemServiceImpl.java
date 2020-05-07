package com.weigo.search.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.item.service.TbItemCatDubboService;
import com.weigo.dubbo.item.service.TbItemDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbItemCat;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbUserItem;
import com.weigo.search.service.TbItemService;
@Service
public class TbItemServiceImpl implements TbItemService {
	@Autowired
	private TbItemDubboService tbItemDubboService;
	@Autowired
	private TbItemCatDubboService tbItemCatDubboService;
	@Autowired
	private TbUserDubboService tbUserDubboService;
	@Value("${solr.url}")
	private String solrUrl;
	@Override
	public int solrItemInit() throws Exception {
		SolrClient solrClient = new HttpSolrClient(solrUrl);
		
			solrClient.deleteByQuery("*:*");
			solrClient.commit();
		List<TbItem> items= tbItemDubboService.selectTbItem();
		for (TbItem tbItem : items) {
			//商品对应的类目信息
			TbItemCat cat = tbItemCatDubboService.selectCatNameById(tbItem.getCid());
			if(cat==null) {
				cat = new TbItemCat();
				cat.setName("其他");
			}
			//商品对应的描述信息
			SolrInputDocument doc = new SolrInputDocument();
			doc.setField("id", tbItem.getId());
			doc.setField("item_title", tbItem.getTitle());
			doc.setField("item_sell_point", tbItem.getSellPoint());
			doc.setField("item_price",tbItem.getPrice() );
			doc.setField("item_image", tbItem.getImage());
			doc.setField("item_category_name", cat.getName());
			doc.setField("item_update", tbItem.getUpdated());
			TbUserItem tbUserItem = tbUserDubboService.selectUserItemByItemId(tbItem.getId());
			TbUser tbUser = tbUserDubboService.selectUserByUserId(tbUserItem.getUid());
			doc.setField("item_user_name", tbUser.getUsername());
			doc.setField("item_user_role_id", tbUser.getRoleId());
			solrClient.add(doc);
		}
		solrClient.commit();
		
		return 1;
	}
	@Override
	public Map<String, Object> selectItemByQuery(String q, int page, int rows) throws Exception {
		SolrQuery params = new SolrQuery();
		//设置分页条件
		params.setStart(rows*(page-1));
		params.setRows(rows);
		//设置条件
		params.setQuery("item_keywords:"+q);
		//设置高亮
		params.setHighlight(true);
		params.addHighlightField("item_title");
		params.setHighlightSimplePre("<span style='color:red'>");
		params.setHighlightSimplePost("</span>");
		//添加排序
		params.setSort("item_update", ORDER.desc);
		SolrClient solrClient = new HttpSolrClient(solrUrl);
		QueryResponse response = solrClient.query(params);
		
		List<TbItem> listChild = new ArrayList<TbItem>();
		//未高亮内容
		SolrDocumentList listSolr = response.getResults();
		//高亮内容
		Map<String, Map<String, List<String>>> hh = response.getHighlighting();
		
		for (SolrDocument doc : listSolr) {
			TbItem child = new TbItem();
			child.setId(Long.parseLong(doc.getFieldValue("id").toString()));
			List<String> list = hh.get(doc.getFieldValue("id")).get("item_title");
			if(list!=null&&list.size()>0){
				child.setTitle(list.get(0));
			}else{
				child.setTitle(doc.getFieldValue("item_title").toString());
			}
			child.setPrice((Long)doc.getFieldValue("item_price"));
			Object image = doc.getFieldValue("item_image");
			child.setImages(image==null||image.equals("")?new String[1]:image.toString().split(","));
			child.setSellPoint(doc.getFieldValue("item_sell_point").toString());
			child.setUsername(doc.getFieldValue("item_user_name").toString());
			child.setRoleId(doc.getFieldValue("item_user_role_id").toString());
			listChild.add(child);
		}
		
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("itemList", listChild);
		resultMap.put("totalPages", listSolr.getNumFound()%rows==0?listSolr.getNumFound()/rows:listSolr.getNumFound()/rows+1);
		resultMap.put("count",listSolr.getNumFound());
		
		return resultMap;
	}
	@Override
	public int insertTbItemtoSolr(TbItem tbItem) {
		Date date = new Date();
		tbItem.setUpdated(date);
		SolrClient solrClient = new HttpSolrClient(solrUrl);
		//商品对应的类目信息
		TbItemCat cat = tbItemCatDubboService.selectCatNameById(tbItem.getCid());
		//商品对应的描述信息
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", tbItem.getId());
		doc.setField("item_title", tbItem.getTitle());
		doc.setField("item_sell_point", tbItem.getSellPoint());
		doc.setField("item_price",tbItem.getPrice() );
		doc.setField("item_image", tbItem.getImage());
		if(cat==null) {
			doc.setField("item_category_name", "其他");	
		}else {
			
			doc.setField("item_category_name", cat.getName());
		}
		doc.setField("item_update", tbItem.getUpdated());
		TbUserItem tbUserItem = tbUserDubboService.selectUserItemByItemId(tbItem.getId());
		TbUser tbUser = tbUserDubboService.selectUserByUserId(tbUserItem.getUid());
		doc.setField("item_user_name", tbUser.getUsername());
		doc.setField("item_user_role_id", tbUser.getRoleId());
		try {
			solrClient.add(doc);
			solrClient.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 1;
	}
	@Override
	public int deleteItemByIds(String ids) throws Exception {
		SolrClient solrClient = new HttpSolrClient(solrUrl);
		List<TbItem> selectItemByIds = tbItemDubboService.selectItemByIds(ids);
		for (TbItem tbItem : selectItemByIds) {
			 Long id = tbItem.getId();
			solrClient.deleteByQuery("id:"+id);
			solrClient.commit();
		}
		
		return 1;
	}
	@Override
	public int insertItemsByIds(String ids) throws Exception {
		Date date = new Date();
		SolrClient solrClient = new HttpSolrClient(solrUrl);
		List<TbItem> selectItemByIds = tbItemDubboService.selectItemByIds(ids);
		for (TbItem tbItem : selectItemByIds) {
			TbItemCat cat = tbItemCatDubboService.selectCatNameById(tbItem.getCid());
			//商品对应的描述信息
			SolrInputDocument doc = new SolrInputDocument();
			doc.setField("id", tbItem.getId());
			doc.setField("item_title", tbItem.getTitle());
			doc.setField("item_sell_point", tbItem.getSellPoint());
			doc.setField("item_price",tbItem.getPrice() );
			doc.setField("item_image", tbItem.getImage());
			
			if(cat==null) {
				doc.setField("item_category_name", "其他");	
			}else {
				
				doc.setField("item_category_name", cat.getName());
			}
			doc.setField("item_update", date);
			TbUserItem tbUserItem = tbUserDubboService.selectUserItemByItemId(tbItem.getId());
			TbUser tbUser = tbUserDubboService.selectUserByUserId(tbUserItem.getUid());
			doc.setField("item_user_name", tbUser.getUsername());
			doc.setField("item_user_role_id", tbUser.getRoleId());
			solrClient.add(doc);
		}
		solrClient.commit();
		return 1;
	}

}
