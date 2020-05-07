package com.weigo.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.weigo.dubbo.user.service.TbPermissionDubboService;
import com.weigo.pojo.TbPermission;
import com.weigo.user.service.TbPermissionService;

@Service
public class TbPermissionServiceImpl implements TbPermissionService{

	@Autowired
	private TbPermissionDubboService tbPermissionDubboService;
	@Override
	public PageInfo<TbPermission> getPageInfo(String keyword, int pageNum, int pageSize) {
		PageInfo<TbPermission> pageInfo = tbPermissionDubboService.getPageInfo(keyword, pageNum, pageSize);
		
		List<TbPermission> list = pageInfo.getList();
		for (TbPermission tbPermission : list) {
			if(tbPermission.getParentId()!=null) {
				TbPermission tbPermission2 = tbPermissionDubboService.selectTbPermissionById(tbPermission.getParentId());
				if(tbPermission2!=null)
				tbPermission.setpName(tbPermission2.getName());
			}
		}
		
		return pageInfo;
	}

	@Override
	public int deleteTbPermissionById(Long permissionId) {
		return tbPermissionDubboService.deleteTbPermissionById(permissionId);
	}

	@Override
	public List<TbPermission> selectTbPermissionByType(String type) {
		return tbPermissionDubboService.selectTbPermissionByType(type);
	}

	@Override
	public TbPermission selectTbPermissionById(Long permissionId) {
		return tbPermissionDubboService.selectTbPermissionById(permissionId);
	}

	@Override
	public int insertBySelective(TbPermission permission) {
		return tbPermissionDubboService.insertBySelective(permission);
	}

	@Override
	public int updateBySelective(TbPermission permission) {
		return tbPermissionDubboService.updateBySelective(permission);
	}

	@Override
	public boolean selectTbPermissionByName(String name) {
		return tbPermissionDubboService.selectTbPermissionByName(name);
	}

	@Override
	public List<TbPermission> selectTbPermissionAll() {
		return tbPermissionDubboService.selectTbPermissionAll();
	}

	@Override
	public List<TbPermission> selectTbPermissionExpressionByIds(List<Long> permissionIdsList) {
		return tbPermissionDubboService.selectTbPermissionExpressionByIds(permissionIdsList);
	}	
}
