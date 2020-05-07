package com.weigo.visitor.service;

import java.util.Date;
import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.VisitorPage;
import com.weigo.pojo.TbUserLogin;

public interface TbUserLoginService {

	PageInfo<TbUserLogin> getUserLoginList(String keyword, int pageSize, int pageNum, String sort, String sortOrder,
			Date startDate, Date endDate,String username);

	MessageObject deleteUserLoginByIds(String ids);
    VisitorPage selectVisitorPageByDate(Date date);
}
