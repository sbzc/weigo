package com.weigo.item.service;

import java.util.List;

import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.ZTreeObject;
import com.weigo.pojo.TbContentCategory;


public interface TbContentCategoryService {

	List<ZTreeObject> getContentCategory(Long id);

	MessageObject insertContentCategory(TbContentCategory category);

	MessageObject deleteContentCategory(TbContentCategory category);

	MessageObject updateContentCategory(TbContentCategory category);

}
