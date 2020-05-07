package com.weigo.search.service.impl;

import java.util.Date;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.weigo.commons.utils.IDUtils;
import com.weigo.dubbo.visitor.service.TbVisitorDubboService;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbVisitor;
import com.weigo.search.service.TbVisitorService;
@Service
public class TbVisitorServiceImpl implements TbVisitorService{

	@Autowired
	private TbVisitorDubboService tbVisitorDubboService;
	@Override
	public void insertVisitor(Integer searchPage) {
		try {
			Date date = new Date();
			TbUser tbUser = (TbUser) SecurityUtils.getSubject().getPrincipal();
			TbVisitor tbVisitor = new TbVisitor();
			tbVisitor.setId(IDUtils.genItemId());
			tbVisitor.setUpdated(date);
			tbVisitor.setUpdatedTime(date);
			tbVisitor.setVisitorWhat(searchPage);
			tbVisitor.setVisitorName(tbUser.getUsername());
			tbVisitorDubboService.insertVisitorByTbVisitor(tbVisitor);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
