package com.weigo.dubbo.visitor.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weigo.dubbo.visitor.service.TbUserLoginDubboService;
import com.weigo.mapper.TbUserLoginMapper;
import com.weigo.pojo.TbUserLogin;
import com.weigo.pojo.TbUserLoginExample;
import com.weigo.pojo.TbUserLoginExample.Criteria;
@Service
public class TbUserLoginDubboServiceImpl implements TbUserLoginDubboService {
    @Autowired
	private TbUserLoginMapper tbUserLoginMapper;
	
	@Override
	public PageInfo<TbUserLogin> getUserLoginList(String keyword, int pageSize, int pageNum, String sort, String sortOrder,
			Date startDate, Date endDate,String username) {
	      PageHelper.startPage(pageNum, pageSize);
	      TbUserLoginExample example = new TbUserLoginExample();
	      Criteria criteria = example.createCriteria();
	      if(startDate!=null) {
              criteria.andLoginDateGreaterThanOrEqualTo(startDate);	    	  
	      }
	      
	      if(endDate!=null) {
	    	  criteria.andLoginDateLessThanOrEqualTo(endDate);
	      }
	      if(StringUtils.isNotBlank(username)) {
	    	  criteria.andUsernameEqualTo(username);
	      }
	      if(StringUtils.isNotBlank(keyword)) {
	    	  criteria.andEmailLike("%"+keyword+"%");
	      }
	      example.setOrderByClause(sort+" "+sortOrder);
		 List<TbUserLogin> tbUserlogins = tbUserLoginMapper.selectByExample(example);
	     PageInfo<TbUserLogin> pageInfo = new PageInfo<TbUserLogin>(tbUserlogins);
		return pageInfo;
	}

	@Override
	public int deleteUserLoginByIds(String ids) {
		String[] idArry = ids.split(",");
		List<Long> idList = new ArrayList<Long>();
 		for (String id : idArry) {
			idList.add(Long.parseLong(id));
		}
		TbUserLoginExample example = new TbUserLoginExample();
		 example.createCriteria().andIdIn(idList);
		int row = tbUserLoginMapper.deleteByExample(example );
		if(row==idList.size()) {
			return 1;
		}
		return 0;
	}

	@Override
	public Long selectUserLoginCountByDate(Date time) {
		TbUserLoginExample example = new TbUserLoginExample();
		example.createCriteria().andLoginDateEqualTo(time);
		return tbUserLoginMapper.countByExample(example);
	}

}
