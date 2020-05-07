package com.weigo.dubbo.item.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.dubbo.item.service.TbContentDubboService;
import com.weigo.mapper.TbContentMapper;
import com.weigo.pojo.TbContent;
import com.weigo.pojo.TbContentExample;
@Service
public class TbContentDubboServiceImpl implements TbContentDubboService{

	@Autowired
	private TbContentMapper tbContentMapper;
	@Override
	public PageInfo<TbContent> selectContentBycategoryId(Long categoryId, int page, int rows) {
         
		PageHelper.startPage(page, rows);
		
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		
		return pageInfo;
	}
	@Override
	public int insertContent(TbContent content) {
		return tbContentMapper.insertSelective(content);
	}
	@Override
	public int deleteContentByIds(String ids) throws Exception {
		String[] idsStr = ids.split(",");
		int rows = 0;
		try {
			for (String id : idsStr) {
				rows +=tbContentMapper.deleteByPrimaryKey(Long.valueOf(id));
			}

		} catch (Exception e) {
		
		}
		if(rows!=idsStr.length) {
			throw new Exception("É¾³ýÊ§°Ü");
			
		}else {
			return 1;
		}
		
		
	}
	@Override
	public List<TbContent> selectcontentByCount(int i) {
		
		PageHelper.startPage(1, i);
		TbContentExample example = new TbContentExample();
		example.setOrderByClause("updated desc");
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example );
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		
		return pageInfo.getList();
	}
	@Override
	public List<TbContent> selectItemByCountAndCategoryId(Integer pageSize, Long categoryId) {
		PageHelper.startPage(1, pageSize);
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		example.setOrderByClause("updated desc");
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example );
		PageInfo<TbContent> info = new PageInfo<TbContent>(list);
		return info.getList();
	}
	@Override
	public int updateContentByContent(TbContent content) {
		
		int ok = tbContentMapper.updateByPrimaryKey(content);
		
		return ok;
	}

}
