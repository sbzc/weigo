package com.weigo.dubbo.item.service;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbItemDesc;

public interface TbItemDubboService {
	PageInfo<TbItem> selectTbItemAll(String keyword, int pageSize, int pageNum,String sort,String sortOrder);

	int deleteItemByIds(String id);

	int updateItemByTbItem(String ids,int status);

	int insertItemByItemAndTbItemDesc(TbItem item, TbItemDesc tbItemDesc,Long uid);

	TbItem selectTbItemById(long id);

	int restUpdateItemByItemAndTbitemDesc(TbItem item, TbItemDesc tbItemDesc);

	PageInfo<TbItem> selectItemByItemIds(List<Long> ids, String keyword, int pageSize, int pageNum, String sort,
			String sortOrder);

	List<TbItem> selectItemByCount(Integer pageSize);

	List<TbItem> selectTbItem();

	int updateItemByTbItemObj(TbItem tbItem);

	List<TbItem> selectItemByIds(String ids);

	List<TbItem> selectItemByCatIdAndCount(Long cid, int i);

	Long selectItemAddCharMessageByDate(Date date);

}
