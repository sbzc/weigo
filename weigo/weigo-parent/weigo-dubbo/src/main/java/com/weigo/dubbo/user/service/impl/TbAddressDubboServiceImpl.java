package com.weigo.dubbo.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.user.service.TbAddressDubboService;
import com.weigo.mapper.TbUserAddressMapper;
import com.weigo.pojo.TbUserAddress;
import com.weigo.pojo.TbUserAddressExample;
@Service
public class TbAddressDubboServiceImpl implements TbAddressDubboService {

	@Autowired
	private TbUserAddressMapper tbUserAddressMapper;
	@Override
	public int insertAddressByTbUserAddress(TbUserAddress tbUserAddress) {
		
		return tbUserAddressMapper.insert(tbUserAddress);
	}
	@Override
	public List<TbUserAddress> selectTbUserAddressByUid(Long uid){
		
		TbUserAddressExample example = new TbUserAddressExample() ;
		example.createCriteria().andUidEqualTo(uid);
		return tbUserAddressMapper.selectByExample(example);
	}
	@Override
	public TbUserAddress selectTbUserAddressById(Long id) {
		return tbUserAddressMapper.selectByPrimaryKey(id);
	}
	@Override
	public int updateAddressByTbUserAddress(TbUserAddress tbUserAddress) {
		return tbUserAddressMapper.updateByPrimaryKeySelective(tbUserAddress);
	}
	@Override
	public int deleteAddressById(Long id) {
		return tbUserAddressMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int selectTbUserAddressCountByUid(Long id) {
		TbUserAddressExample example= new TbUserAddressExample();
		example.createCriteria().andUidEqualTo(id);
		return (int) tbUserAddressMapper.countByExample(example);
	}

}
