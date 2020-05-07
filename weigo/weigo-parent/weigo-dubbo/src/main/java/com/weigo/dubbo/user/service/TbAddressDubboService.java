package com.weigo.dubbo.user.service;

import java.util.List;

import com.weigo.pojo.TbUserAddress;

public interface TbAddressDubboService {

	int insertAddressByTbUserAddress(TbUserAddress tbUserAddress);


	TbUserAddress selectTbUserAddressById(Long id);

	int updateAddressByTbUserAddress(TbUserAddress tbUserAddress);

	int deleteAddressById(Long id);

	List<TbUserAddress> selectTbUserAddressByUid(Long uid);


	int selectTbUserAddressCountByUid(Long id);

}
