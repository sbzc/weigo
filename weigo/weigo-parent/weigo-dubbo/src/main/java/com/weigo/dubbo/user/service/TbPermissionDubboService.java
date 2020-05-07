package com.weigo.dubbo.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.weigo.pojo.TbPermission;


public interface TbPermissionDubboService {

	PageInfo<TbPermission> getPageInfo(String keyword, int pageNum, int pageSize);

	int deleteTbPermissionById(Long permissionId);

	List<TbPermission> selectTbPermissionByType(String type);

	TbPermission selectTbPermissionById(Long permissionId);

	int insertBySelective(TbPermission permission);

	int updateBySelective(TbPermission permission);

	boolean selectTbPermissionByName(String name);

	List<TbPermission> selectTbPermissionAll();

	List<TbPermission> selectTbPermissionExpressionByIds(List<Long> permissionIdsList);

}
