package com.weigo.portal.service;

import java.util.List;

import com.weigo.pojo.TbItemCat;
import com.weigo.portal.pojo.PortalMessage;

public interface TbItemCatService {

	List<TbItemCat> selectTbItemCatByPid(int i);

	PortalMessage selectPortalMessag();

}
