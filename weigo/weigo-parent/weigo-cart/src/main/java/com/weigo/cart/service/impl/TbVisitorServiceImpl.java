package com.weigo.cart.service.impl;

import java.util.Date;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.cart.service.TbVisitorService;
import com.weigo.commons.utils.IDUtils;
import com.weigo.dubbo.visitor.service.TbVisitorDubboService;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbVisitor;
@Service
public class TbVisitorServiceImpl implements TbVisitorService{

	@Autowired
	private TbVisitorDubboService tbVisitorDubboService;
	@Override
	public void insertVisitor(Integer cartPage) {
		try {
			Date date = new Date();
			TbUser tbUser = (TbUser) SecurityUtils.getSubject().getPrincipal();
			TbVisitor tbVisitor = new TbVisitor();
			tbVisitor.setId(IDUtils.genItemId());
			tbVisitor.setUpdated(date);
			tbVisitor.setUpdatedTime(date);
			
			tbVisitor.setVisitorWhat(cartPage);
			tbVisitor.setVisitorName(tbUser.getUsername());
			tbVisitorDubboService.insertVisitorByTbVisitor(tbVisitor);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
