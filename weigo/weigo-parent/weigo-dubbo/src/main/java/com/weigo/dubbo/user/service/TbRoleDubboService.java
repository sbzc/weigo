package com.weigo.dubbo.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.weigo.pojo.TbRole;

public interface TbRoleDubboService {

	TbRole selectRoleNameById(Long roleId);

	List<TbRole> selectRoleByAll();
	
	PageInfo<TbRole> getPageInfo(String keyword, int pageNum, int pageSize);

	int deleteTbRoleById(Long roleId);

	TbRole selectTbRoleById(Long roleId);

	int insertTbRoleBySelective(TbRole role);

	int updeteTbRoleBySelective(TbRole role);

	TbRole selectTbRolePermissionByTbRoleId(Long roleId);

	TbRole selectRoleByRoleName(String string);
}
