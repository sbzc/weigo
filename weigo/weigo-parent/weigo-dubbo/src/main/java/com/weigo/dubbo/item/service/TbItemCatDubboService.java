package com.weigo.dubbo.item.service;

import java.util.List;

import com.weigo.pojo.TbItemCat;

public interface TbItemCatDubboService {

	List<TbItemCat> selectItemCatByPid(long pid);

	TbItemCat selectCatNameById(Long cid);

	int insertTbItemCatByTbItemCat(TbItemCat tbItemCat);

	int updateTbItemCatByObj(TbItemCat parent);

	int deleteCatById(Long id);

}
