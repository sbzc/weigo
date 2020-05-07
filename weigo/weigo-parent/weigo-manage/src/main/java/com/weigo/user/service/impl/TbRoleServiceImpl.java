package com.weigo.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.VisitorPage;
import com.weigo.commons.pojo.VisitorPageMessage;
import com.weigo.dubbo.user.service.TbRoleDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.pojo.TbRole;
import com.weigo.user.service.TbRoleService;
@Service
public class TbRoleServiceImpl implements TbRoleService {
	@Autowired
    private TbRoleDubboService tbRoleDubboService;
	@Autowired
	private TbUserDubboService tbUserDubboService;
	@Override
	public List<TbRole> selectTbRoleByAll() {
		return tbRoleDubboService.selectRoleByAll();
	}
	
	@Override
	public PageInfo<TbRole> getPageInfo(String keyword, int pageNum, int pageSize) {
		
		return tbRoleDubboService.getPageInfo(keyword, pageNum, pageSize);
	}
	@Override
	public int deleteTbRoleById(Long roleId) {
		return tbRoleDubboService.deleteTbRoleById(roleId);
	}
	@Override
	public TbRole selectTbRoleById(Long roleId) {
		return tbRoleDubboService.selectTbRoleById(roleId);
	}
	@Override
	public int insertTbRoleBySelective(TbRole role) {
		return tbRoleDubboService.insertTbRoleBySelective(role);
	}
	@Override
	public int updeteTbRoleBySelective(TbRole role) {
		return tbRoleDubboService.updeteTbRoleBySelective(role);
	}
	@Override
	public TbRole selectTbRolePermissionByTbRoleId(Long roleId) {
		return tbRoleDubboService.selectTbRolePermissionByTbRoleId(roleId);
	}
   //查询某个角色有多少用户。用于统计饼图
	@Override
	public VisitorPage selectRoleChartMessage() {
		VisitorPage visitorPage = new VisitorPage();
		List<VisitorPageMessage> visitors = new ArrayList<>(); 
		List<TbRole> roles = tbRoleDubboService.selectRoleByAll();
		for (TbRole tbRole : roles) {
			VisitorPageMessage v = new VisitorPageMessage();
			Long count = tbUserDubboService.selectUserCountByRoleId(tbRole.getRoleId());
			v.setCount(count);
			v.setNamePage(tbRole.getRolename());
			visitors.add(v);
		}
		visitorPage.setValues(visitors);
		return visitorPage;
	}
   
}
