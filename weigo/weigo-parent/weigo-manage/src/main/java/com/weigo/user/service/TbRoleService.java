package com.weigo.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.VisitorPage;
import com.weigo.pojo.TbRole;

public interface TbRoleService {

	List<TbRole> selectTbRoleByAll();

	PageInfo<TbRole> getPageInfo(String keyword, int pageNum, int pageSize);

	int deleteTbRoleById(Long roleId);

	TbRole selectTbRoleById(Long roleId);

	int insertTbRoleBySelective(TbRole role);

	int updeteTbRoleBySelective(TbRole role);

	TbRole selectTbRolePermissionByTbRoleId(Long roleId);

	VisitorPage selectRoleChartMessage();
}
