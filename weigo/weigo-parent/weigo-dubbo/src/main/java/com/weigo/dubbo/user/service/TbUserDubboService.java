package com.weigo.dubbo.user.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbUserItem;

public interface TbUserDubboService {
   TbUser selectUserByUsername(String username);
   
   TbUserItem selectUserItemByItemId(Long id);

TbUser selectUserByUserId(Long id);

PageInfo<TbUser> selectTbUserByKeyword(String keyword, int pageSize, int pageNum, String sort, String sortOrder);

int insertUserByTbUser(TbUser tbUser)throws Exception;

TbUser selectUserById(Long id);

int updateUserByTbUser(TbUser tbUser);

int updateUserByIdsAndStatus(String ids, Integer status)  throws Exception;

List<TbUserItem> selectUserItemByUserId(Long uid);

List<TbUser> selectUserByAll();

List<TbUser> selectUserByPhone(String phone);

List<TbUser> selectUserByEmail(String email);

TbUser selectTbUserByRoleId(Long roleId);

Long selectUserCountByRoleId(Long roleId);

Long selectUserChartMessage(Date time);
   
}
