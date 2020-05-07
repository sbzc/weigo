package com.weigo.user.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.pojo.UserMessage;
import com.weigo.commons.pojo.VisitorPage;
import com.weigo.manage.pojo.UserSuggestData;
import com.weigo.pojo.TbUser;

public interface TbUserService {

	PageInfo<TbUser> selectItemByKeyword(String keyword, int pageSize, int pageNum, String sort, String sortOrder);

	int selectUserByUsername(String username);

	int insertUserByIbUser(TbUser tbUser)throws Exception;

	TbUser selectUserById(Long id);

	 MessageObject updateUserByTbUser(TbUser tbUser);

	int updateUserByIdsAndStatus(String ids, Integer status);

	List<UserSuggestData> selectUserIdAndUserNameByAll();

	UserMessage getUserMessageByUid(Long id);

	UserMessage getUserMessageByOrderItemId(String orderItemId);

	Map<String, Long> getPendingCountByUid();

	boolean addCheckPhone(String phone);

	boolean addCheckEmail(String email);

	Map<String, String> selectAdmin();

	MessageObject getMailCodeByEmail(String email,HttpServletRequest req);

	MessageObject insertUserByTbUserAndverifyCode(TbUser user, String verifyCode,HttpServletRequest req);

	MessageObject getMailCodeByEmailAndCheckEmail(String email, HttpServletRequest req);

	MessageObject updateUserPassword(String email, String password, String verifyCode,HttpServletRequest req);

	VisitorPage selectUserChartMessage(Date date);

}
