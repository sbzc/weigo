package com.weigo.dubbo.item.service;

import java.util.List;

import com.weigo.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {

	List<TbContentCategory> getContentCategory(Long id);

	int insertContentCategory(TbContentCategory category);


	TbContentCategory selectContentCategoryById(Long id);

	int updIsParentById(TbContentCategory category);

}
