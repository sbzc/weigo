package com.weigo.user.service.impl;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.commons.utils.IDUtils;
import com.weigo.dubbo.user.service.TbEvaluateDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.pojo.TbEvaluate;
import com.weigo.pojo.TbUser;
import com.weigo.user.service.TbEvaluateService;
@Service
public class TbEvaluateServiceImpl implements TbEvaluateService {

	@Autowired
	private TbEvaluateDubboService tbEvaluateDubboService;
	@Autowired
	private TbUserDubboService tbUserDubboService;
	@Override
	public MessageObject insertEvaluateByTbEvaluateAndOrderItemId(TbEvaluate tbEvaluate, String orderItemId) {
		
		MessageObject mo = new MessageObject();
		TbUser user = (TbUser) SecurityUtils.getSubject().getPrincipal();
		mo.setCode(0);
		Date date = new Date();
		tbEvaluate.setId(IDUtils.genItemId());
		tbEvaluate.setCreated(date);
		Long uid=null;
		try {
			uid=tbEvaluateDubboService.insertEvaluateByTbEvaluateAndOrderItemIdAndUid(tbEvaluate,orderItemId,user.getId());
		} catch (Exception e) {
			mo.setMsg(e.getMessage());
			
			e.printStackTrace();
			return mo;
		}
		if(uid!=null) {
			long tbScore = tbEvaluate.getEvaluatescore().longValue();
			int evaluatescore = (int) (tbScore-3);
			TbUser tbUser = tbUserDubboService.selectUserByUserId(uid);
			tbUser.setUpdated(date);
			Integer tbscore = tbUser.getScore();
			int score = tbscore+evaluatescore;
			if(score<1) {
				score=1;
			}else if(score>50) {
				score=50;
			}
			tbUser.setScore(score);
			tbUser.setRoleId(score/10l);
			int ok = tbUserDubboService.updateUserByTbUser(tbUser);
			if(ok==1) {
				mo.setCode(1);
				mo.setMsg("评价成功");
				return mo;
			}else {
				mo.setMsg("评价出错");
			}
		}else {
			mo.setMsg("无法评价");
		}
		
		
		
		return mo;
	}
	
	@Override
	public PageInfo<TbEvaluate> selectUserEvalutePageInfo(Long id, String keyword, String sort, String sortOrder,
			int pageSize, int pageNum) {
		      
		return  this.tbEvaluateDubboService.selectEvaluatePageInfoByMoreParm(id,keyword,sort,sortOrder,pageSize,pageNum);
	}

	@Override
	public MessageObject deleteEvaluateById(Long id) {
		MessageObject mo = new MessageObject();
		int ok = tbEvaluateDubboService.deleteEvaluateById(id);
		mo.setCode(ok);
		if(ok==1) {
			mo.setMsg("删除成功");
		}else {
			mo.setMsg("删除失败");
		}
		return mo;
	}

}
