package com.weigo.item.service;



import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.VisitorPage;
import com.weigo.pojo.TbItem;

public interface TbItemService {
	PageInfo<TbItem> selectItemByAll(String keyword, int pageSize, int pageNum,String sort,String sortOrder);

	int deleteItemByIds(String id);

	int updateItemByTbItem(String ids,int status);

	int insertItemByItemAndDesc(TbItem item, String desc);

	TbItem selectItemById(long id);

	int restUpdateItem(TbItem item);

	PageInfo<TbItem> selectItemByUid(String keyword, int pageSize, int pageNum, String sort, String sortOrder,
			String username);

	VisitorPage selectItemAddCharMessageByDate(Date date);
}
