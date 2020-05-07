package com.weigo.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.user.service.TbEvaluateDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.pojo.TbEvaluate;
import com.weigo.pojo.TbUser;
import com.weigo.portal.service.TbEvaluateService;
@Service
public class TbEvaluateServiceImpl implements TbEvaluateService {

	@Autowired
	private TbEvaluateDubboService tbEvaluateDubboService;
	@Autowired
	private TbUserDubboService tbUserDubboService;
	@Override
	public List<TbEvaluate> selectUserEvaluateByUsernmae(String username) {
		TbUser tbUser = tbUserDubboService.selectUserByUsername(username);
		if(tbUser==null) {
			return null;
		}
		
		return tbEvaluateDubboService.selectEvaluateByUid(tbUser.getId());
	}

}
