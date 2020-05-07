package com.weigo.item.service;

import com.weigo.commons.pojo.EasyUIDataGrid;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbContent;

public interface TbContentService {

	EasyUIDataGrid selectContent(Long categoryId, int page, int rows);

	MessageObject insertContent(TbContent content);

	MessageObject deleteContentByIds(String ids);

	MessageObject updateContentByContent(TbContent content);

}
