package com.weigo.dubbo.user.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weigo.dubbo.user.service.TbRoleDubboService;
import com.weigo.mapper.TbRoleMapper;
import com.weigo.mapper.TbUserMapper;
import com.weigo.pojo.TbRole;
import com.weigo.pojo.TbRoleExample;
import com.weigo.pojo.TbRoleExample.Criteria;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbUserExample;
@Service
public class TbRoleDubboServiceImpl implements TbRoleDubboService{

	@Autowired
	private TbRoleMapper tbRoleMapper;
	@Autowired
	private TbUserMapper tbUserMapper;
	@Override
	public TbRole selectRoleNameById(Long roleId) {
		return tbRoleMapper.selectByPrimaryKey(roleId);
	}
	@Override
	public List<TbRole> selectRoleByAll() {
		
		return tbRoleMapper.selectByExample(new TbRoleExample());
	}
	@Override
	public PageInfo<TbRole> getPageInfo(String keyword, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbRoleExample example = new TbRoleExample();
		
		if(StringUtils.isNotBlank(keyword)) {
			Criteria criteria = example.createCriteria();
			criteria.andRolenameLike("%"+keyword+"%");
		}
		
		List<TbRole> roles = tbRoleMapper.selectByExample(example );
		PageInfo<TbRole> pageInfo = new PageInfo<TbRole>(roles);
		
		return pageInfo;
	}
	@Override
	public int deleteTbRoleById(Long roleId) {
		int row = 0;
		TbUserExample example = new TbUserExample();
		com.weigo.pojo.TbUserExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		List<TbUser> users = tbUserMapper.selectByExample(example );
		if(users.size()>0) {
			row = 2;
			return row;
		}
		row = tbRoleMapper.deleteByPrimaryKey(roleId);
		
		return row;
	}
	@Override
	public TbRole selectTbRoleById(Long roleId) {
		return tbRoleMapper.selectByPrimaryKey(roleId);
	}
	@Override
	public int insertTbRoleBySelective(TbRole role) {
		return tbRoleMapper.insertSelective(role);
	}
	@Override
	public int updeteTbRoleBySelective(TbRole role) {
		
		return tbRoleMapper.updateByPrimaryKeySelective(role);
	}
	@Override
	public TbRole selectTbRolePermissionByTbRoleId(Long roleId) {
		return tbRoleMapper.selectByPrimaryKey(roleId);
	}
	@Override
	public TbRole selectRoleByRoleName(String string) {
		
		TbRoleExample example = new TbRoleExample();
		example.createCriteria().andRolenameEqualTo(string);
		return tbRoleMapper.selectByExample(example ).get(0);
	}

	
}
