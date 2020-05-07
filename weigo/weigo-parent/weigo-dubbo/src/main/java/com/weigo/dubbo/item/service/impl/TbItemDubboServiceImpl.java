package com.weigo.dubbo.item.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weigo.dubbo.item.service.TbItemDubboService;
import com.weigo.mapper.TbItemDescMapper;
import com.weigo.mapper.TbItemMapper;
import com.weigo.mapper.TbUserItemMapper;
import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbItemDesc;
import com.weigo.pojo.TbItemExample;
import com.weigo.pojo.TbItemExample.Criteria;
import com.weigo.pojo.TbUserItem;
@Service
public class TbItemDubboServiceImpl implements TbItemDubboService {
	@Autowired
    private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbUserItemMapper tbUserItemMapper;
	@Override
	public PageInfo<TbItem> selectTbItemAll(String keyword, int pageSize, int pageNum,String sort,String sortOrder) {
        PageHelper.startPage(pageNum, pageSize);
        TbItemExample example = new TbItemExample();
        if(StringUtils.isNotBlank(keyword)) {
        	example.createCriteria().andTitleLike("%"+keyword+"%");
        }

        example.setOrderByClause(sort+" "+sortOrder);
		List<TbItem> list = tbItemMapper.selectByExample(example );
        PageInfo<TbItem> info = new PageInfo<TbItem>(list);
		return info;
	}
	@Override
	public int deleteItemByIds(String ids) {
		String[] idsStr = ids.split(",");
		int row=0;
		for (String id : idsStr) {
			row+=tbItemMapper.deleteByPrimaryKey(Long.parseLong(id));
			row+=tbItemDescMapper.deleteByPrimaryKey(Long.parseLong(id));
			
		}
		if(row==(idsStr.length*2)) {
			return 1;
		}else {
			
		}
		
		return 0;
	}
	@Override
	public int updateItemByTbItem(String ids,int status) {
		
	  
		String[] idsStr = ids.split(",");
		int row = 0;
		Date date = new Date();
		for (String id : idsStr) {
			TbItem tbItem = new TbItem();
			tbItem.setId(Long.parseLong(id));
			tbItem.setStatus((byte) status);
			tbItem.setUpdated(date);
			row += this.tbItemMapper.updateByPrimaryKeySelective(tbItem);
		}
		if(row==idsStr.length) {
			return 1;
		}
		return 0;
	}
	@Override
	public int insertItemByItemAndTbItemDesc(TbItem item, TbItemDesc tbItemDesc,Long uid) {
		int row=0;
		row = this.tbItemDescMapper.insert(tbItemDesc);
		row+=this.tbItemMapper.insert(item);
		TbUserItem userItem = new TbUserItem();
		userItem.setItemid(item.getId());
		userItem.setUid(uid);
		row+=this.tbUserItemMapper.insert(userItem );
		if(row==3) {
			return 1;
		}else {
			return 0;
		}
	}
	@Override
	public TbItem selectTbItemById(long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}
	@Override
	public int restUpdateItemByItemAndTbitemDesc(TbItem item, TbItemDesc tbItemDesc) {
		int row=0;
		row =this.tbItemMapper.updateByPrimaryKeySelective(item);
		row+=this.tbItemDescMapper.updateByPrimaryKeyWithBLOBs(tbItemDesc);
		if(row==2) {
			return 1;
		}
		return 0;
	}
	@Override
	public PageInfo<TbItem> selectItemByItemIds(List<Long> ids, String keyword, int pageSize, int pageNum, String sort,
			String sortOrder) {
        PageHelper.startPage(pageNum, pageSize);
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(keyword)) {
        	criteria.andTitleLike("%"+keyword+"%");
        }
        if(ids==null||ids.size()==0) {
        	return null;
        }
        criteria.andIdIn(ids);
        example.setOrderByClause(sort+" "+sortOrder);
		List<TbItem> list = tbItemMapper.selectByExample(example );
        PageInfo<TbItem> info = new PageInfo<TbItem>(list);
		return info;
	}
	@Override
	public List<TbItem> selectItemByCount(Integer pageSize) {
		PageHelper.startPage(1, pageSize);
		TbItemExample example = new TbItemExample();
		example.createCriteria().andStatusEqualTo((byte) 1);
		example.setOrderByClause("updated desc");
		List<TbItem> list = tbItemMapper.selectByExample(example );
		PageInfo<TbItem> info = new PageInfo<TbItem>(list);
		return info.getList();
	}
	@Override
	public List<TbItem> selectTbItem() {
		TbItemExample example = new TbItemExample();
		example.createCriteria().andStatusEqualTo((byte)1);
		return tbItemMapper.selectByExample(example);
	}
	@Override
	public int updateItemByTbItemObj(TbItem tbItem) {
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}
	@Override
	public List<TbItem> selectItemByIds(String ids) {
		String[] split = ids.split(",");
		TbItemExample example = new TbItemExample();
		List<Long> values = new ArrayList<Long>();
           for (String string : split) {
        	   values.add(Long.parseLong(string));
		}
		example.createCriteria().andIdIn(values );
		return tbItemMapper.selectByExample(example );
	}
	@Override
	public List<TbItem> selectItemByCatIdAndCount(Long cid, int i) {
		PageHelper.startPage(1, i);
		TbItemExample example = new TbItemExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("updated desc");
		return tbItemMapper.selectByExample(example );
	}
	@Override
	public Long selectItemAddCharMessageByDate(Date date) {
		System.out.println(date.getTime());
		TbItemExample example = new TbItemExample();
		example.createCriteria().andCreatedEqualTo(date);
		return tbItemMapper.countByExample(example);
	}
	

}
