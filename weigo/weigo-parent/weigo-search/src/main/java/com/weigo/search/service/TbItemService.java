package com.weigo.search.service;

import java.util.Map;

import com.weigo.pojo.TbItem;

public interface TbItemService {

	int solrItemInit() throws Exception ;

	Map<String, Object> selectItemByQuery(String q, int page, int rows)throws Exception ;

	int insertTbItemtoSolr(TbItem tbItem);

	int deleteItemByIds(String ids)throws Exception ;

	int insertItemsByIds(String ids)throws Exception;

}
