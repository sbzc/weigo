package com.weigo.dubbo.user.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weigo.dubbo.user.service.TbEvaluateDubboService;
import com.weigo.mapper.TbEvaluateMapper;
import com.weigo.mapper.TbOrderItemMapper;
import com.weigo.pojo.TbEvaluate;
import com.weigo.pojo.TbEvaluateExample;
import com.weigo.pojo.TbEvaluateExample.Criteria;
import com.weigo.pojo.TbOrderItem;
@Controller
public class TbEvaluateDubboServiceImpl implements TbEvaluateDubboService {

	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	private TbEvaluateMapper tbEvaluateMapper;
	@Override
	public Long insertEvaluateByTbEvaluateAndOrderItemIdAndUid(TbEvaluate tbEvaluate, String orderItemId,Long uid) throws Exception {
		Date date = new Date();
		TbOrderItem tbOrderItem = tbOrderItemMapper.selectByPrimaryKey(orderItemId);
		if(tbOrderItem==null) {
			return null;
		}
		
		if(tbOrderItem.getClientId().longValue()!=uid.longValue()) {
			return null;
		}
		if(tbOrderItem.getBuyerRate().intValue()==1) {
			throw new Exception("已经评价过了");
		}
		tbEvaluate.setUid(tbOrderItem.getSellerId());
		tbOrderItem.setUpdated(date); 
		tbOrderItem.setBuyerRate(1);
		int row=0;
        try {
        	row = tbEvaluateMapper.insert(tbEvaluate);
        	row+=tbOrderItemMapper.updateByPrimaryKeySelective(tbOrderItem);
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
        if(row==2)
    		return tbOrderItem.getSellerId();
        else {
        	throw new Exception("系统出错");
        }
		
	}
	@Override
	public List<TbEvaluate> selectEvaluateByUid(Long uid) {
		TbEvaluateExample example = new TbEvaluateExample();
		example.createCriteria().andUidEqualTo(uid);
		return this.tbEvaluateMapper.selectByExample(example);
	}
	@Override
	public Long selectEvaluateCountByUid(Long uid) {
		TbEvaluateExample example = new TbEvaluateExample();
		example.createCriteria().andUidEqualTo(uid);
		return this.tbEvaluateMapper.countByExample(example);
	}
	@Override
	public PageInfo<TbEvaluate> selectEvaluatePageInfoByMoreParm(Long id, String keyword, String sort, String sortOrder,
			int pageSize, int pageNum) {
		
		PageHelper.startPage(pageNum, pageSize);
		TbEvaluateExample example =new TbEvaluateExample();
		 Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(keyword)) {
			criteria.andEvaluatemsgLike("%"+keyword+"%");
		}
		criteria.andUidEqualTo(id);
		example.setOrderByClause(sort+" "+sortOrder);
		List<TbEvaluate> list = this.tbEvaluateMapper.selectByExample(example );
		PageInfo<TbEvaluate> pageInfo = new PageInfo<TbEvaluate>(list);
		return pageInfo;
	}
	@Override
	public int deleteEvaluateById(Long id) {
		
		return this.tbEvaluateMapper.deleteByPrimaryKey(id);
	}

}
