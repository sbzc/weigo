package com.weigo.user.service;

import java.util.List;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbUserAddress;

public interface TbAddressService {

	MessageObject insertAddressByTbUserAddress(TbUserAddress tbUserAddress);
	TbUserAddress getAddress(Long id);
	int updateAddressByTbUserAddress(TbUserAddress tbUserAddress);
	MessageObject deleteAddressById(Long id);
	List<TbUserAddress> selectAddressByUid();

}
