package com.weigo.portal.service;

import java.util.List;

import com.weigo.pojo.TbEvaluate;

public interface TbEvaluateService {


	List<TbEvaluate> selectUserEvaluateByUsernmae(String username);

}
