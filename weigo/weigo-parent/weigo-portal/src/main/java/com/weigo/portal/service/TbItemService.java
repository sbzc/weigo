package com.weigo.portal.service;


import java.util.Map;

import com.weigo.pojo.TbItem;

public interface TbItemService {



	TbItem selectItemByItemId(Long id);

	Map<String, Object> selectItemAndEqItemByItemId(Long id);

}
