package com.weigo.user.service;


import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.pojo.TbEvaluate;

public interface TbEvaluateService {

	MessageObject insertEvaluateByTbEvaluateAndOrderItemId(TbEvaluate tbEvaluate, String orderItemId);


	PageInfo<TbEvaluate> selectUserEvalutePageInfo(Long id, String keyword, String sort, String sortOrder, int pageSize,
			int pageNum);


	MessageObject deleteEvaluateById(Long id);

}
