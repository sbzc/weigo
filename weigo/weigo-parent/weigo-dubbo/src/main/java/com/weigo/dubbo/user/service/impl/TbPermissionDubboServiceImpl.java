package com.weigo.dubbo.user.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weigo.dubbo.user.service.TbPermissionDubboService;
import com.weigo.mapper.TbPermissionMapper;
import com.weigo.pojo.TbPermission;
import com.weigo.pojo.TbPermissionExample;
import com.weigo.pojo.TbPermissionExample.Criteria;


@Service
public class TbPermissionDubboServiceImpl implements TbPermissionDubboService{
    @Autowired
	private TbPermissionMapper permissionMapper;

	@Override
	public PageInfo<TbPermission> getPageInfo(String keyword, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbPermissionExample example = new TbPermissionExample();
		
		if(StringUtils.isNotBlank(keyword)) {
			Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+keyword+"%");
			
		}
		example.setOrderByClause("type asc");
		
		List<TbPermission> permissions = permissionMapper.selectByExample(example );
		
		PageInfo<TbPermission> pageInfo = new PageInfo<TbPermission>(permissions);
		
		return pageInfo;
	}

	@Override
	public int deleteTbPermissionById(Long permissionId) {
		
		TbPermissionExample example = new TbPermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(permissionId);
		List<TbPermission> permissions = permissionMapper.selectByExample(example);
		
		if(permissions.size()>0) {
			return 2;
		}
		
		return permissionMapper.deleteByPrimaryKey(permissionId);
	}

	@Override
	public List<TbPermission> selectTbPermissionByType(String type) {
		TbPermissionExample example = new TbPermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(type);
		return permissionMapper.selectByExample(example );
	}

	@Override
	public TbPermission selectTbPermissionById(Long permissionId) {
		
		
		return permissionMapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public int insertBySelective(TbPermission permission) {
		return permissionMapper.insertSelective(permission);
	}

	@Override
	public int updateBySelective(TbPermission permission) {
		
		return permissionMapper.updateByPrimaryKeySelective(permission);
	}

	@Override
	public boolean selectTbPermissionByName(String name) {
		
		TbPermissionExample example = new TbPermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<TbPermission> permissions = permissionMapper.selectByExample(example );
		if(permissions.size()>0) {
			return false;
		}
		return true;
	}

	@Override
	public List<TbPermission> selectTbPermissionAll() {
		return permissionMapper.selectByExample(null);
	}

	@Override
	public List<TbPermission> selectTbPermissionExpressionByIds(List<Long> permissionIdsList) {
		TbPermissionExample example = new TbPermissionExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andPermissionIdIn(permissionIdsList);
		
		return permissionMapper.selectByExample(example);
	}
	
	
	
}
