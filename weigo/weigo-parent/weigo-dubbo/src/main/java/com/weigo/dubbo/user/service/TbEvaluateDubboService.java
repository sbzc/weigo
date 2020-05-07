package com.weigo.dubbo.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.weigo.pojo.TbEvaluate;

public interface TbEvaluateDubboService {

	Long insertEvaluateByTbEvaluateAndOrderItemIdAndUid(TbEvaluate tbEvaluate, String orderItemId,Long uid)throws Exception;

	List<TbEvaluate> selectEvaluateByUid(Long uid);

	Long selectEvaluateCountByUid(Long uid);

	PageInfo<TbEvaluate> selectEvaluatePageInfoByMoreParm(Long id, String keyword, String sort, String sortOrder,
			int pageSize, int pageNum);

	int deleteEvaluateById(Long id);


}
