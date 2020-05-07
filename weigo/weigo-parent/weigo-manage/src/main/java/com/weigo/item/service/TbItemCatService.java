package com.weigo.item.service;

import java.util.List;

import com.weigo.commons.pojo.EasyUiTree;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbItemCat;

public interface TbItemCatService {

	List<EasyUiTree> getItemCatByPid(long id);

	MessageObject insertContentCategory(TbItemCat tbItemCat);

	MessageObject deleteContentCategory(TbItemCat tbItemCat);

	MessageObject updateContentCategory(TbItemCat tbItemCat);

}
