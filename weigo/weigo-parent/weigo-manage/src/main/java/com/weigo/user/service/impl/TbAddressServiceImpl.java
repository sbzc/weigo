package com.weigo.user.service.impl;


import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.utils.IDUtils;
import com.weigo.dubbo.user.service.TbAddressDubboService;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbUserAddress;
import com.weigo.user.service.TbAddressService;
@Service 
public class TbAddressServiceImpl implements TbAddressService {

	@Autowired
	private TbAddressDubboService tbAddressDubboService;
	@Override
	public MessageObject insertAddressByTbUserAddress(TbUserAddress tbUserAddress) {
		
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		int count = tbAddressDubboService.selectTbUserAddressCountByUid(user.getId());
		//用户拥有地址不可超过五个
		if(count>=5) {
			mo.setMsg("添加失败！地址上限是5个");
			return mo;
		}
		
		Date date = new Date();
		tbUserAddress.setCreated(date);
		tbUserAddress.setUpdated(date);
		tbUserAddress.setUid(user.getId());
		tbUserAddress.setId(IDUtils.genItemId());
		int ok = tbAddressDubboService.insertAddressByTbUserAddress(tbUserAddress);
		mo.setMsg("添加失败！！");
		mo.setCode(ok);
		if(ok==1) {
			mo.setMsg("添加成功！！");
		}
		return mo;
	}

	@Override
	public TbUserAddress getAddress(Long id) {
		
		return tbAddressDubboService.selectTbUserAddressById(id);
	}

	@Override
	public int updateAddressByTbUserAddress(TbUserAddress tbUserAddress) {
		Date date = new Date();
		tbUserAddress.setUpdated(date);
		return tbAddressDubboService.updateAddressByTbUserAddress(tbUserAddress);
	}

	@Override
	public MessageObject deleteAddressById(Long id) {
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		int row = tbAddressDubboService.deleteAddressById(id);
		if(row==1) {
			mo.setCode(1);
			mo.setMsg("删除成功");
		}else {
			mo.setMsg("删除失败");
		}
		return mo;
	}

	@Override
	public List<TbUserAddress> selectAddressByUid() {
		TbUser principal = (TbUser) SecurityUtils.getSubject().getPrincipal();
		
		return tbAddressDubboService.selectTbUserAddressByUid(principal.getId());
	}

	

}
