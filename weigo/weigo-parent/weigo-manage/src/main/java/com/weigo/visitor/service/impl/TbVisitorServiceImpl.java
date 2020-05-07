package com.weigo.visitor.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.commons.pojo.ConstantObject;
import com.weigo.commons.pojo.VisitorPage;
import com.weigo.commons.pojo.VisitorPageMessage;
import com.weigo.commons.utils.IDUtils;
import com.weigo.dubbo.visitor.service.TbVisitorDubboService;
import com.weigo.pojo.TbUser;
import com.weigo.pojo.TbVisitor;
import com.weigo.visitor.service.TbVisitorService;
@Service
public class TbVisitorServiceImpl implements TbVisitorService{

	@Autowired
	private TbVisitorDubboService tbVisitorDubboService;
	@Override
	public void insertVisitor(Integer managepage) {
		try {
			Date date = new Date();
			TbUser tbUser = (TbUser) SecurityUtils.getSubject().getPrincipal();
			TbVisitor tbVisitor = new TbVisitor();
			tbVisitor.setId(IDUtils.genItemId());
			tbVisitor.setUpdated(date);
			tbVisitor.setUpdatedTime(date);
			tbVisitor.setVisitorWhat(managepage);
			tbVisitor.setVisitorName(tbUser.getUsername());
			tbVisitorDubboService.insertVisitorByTbVisitor(tbVisitor);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	@Override
	public List<VisitorPage> selectVisitorConunt(Date date) {
		List<VisitorPage> lists = new ArrayList<>();
		VisitorPage visitorPage = getVisitorPage(null,"zhu");
		lists.add(visitorPage);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);//����
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-7);
		
		for(int i=0;i<7;i++) {
			String key = calendar.get(Calendar.MONDAY)+1+"��"+calendar.get(Calendar.DAY_OF_MONTH)+"��";
			lists.add(getVisitorPage(calendar.getTime(),key));
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
		}
		return lists;
	}
	
	private VisitorPage getVisitorPage(Date date,String key){
		VisitorPage visitorPage = new VisitorPage();
		List<VisitorPageMessage> visitorsTmp = new ArrayList<VisitorPageMessage>();
		List<Map<String,Object>> tmp = tbVisitorDubboService.selectByGroup(date);	
		int [] p =new int[10];
			for (Map<String, Object> imap : tmp) {
				VisitorPageMessage v = new VisitorPageMessage();
			    int k = (Integer)imap.get("what");
			   v.setNamePage(ConstantObject.visitorPageArray[k]);
			   v.setCount((Long)imap.get("count"));
			   p[k]=1;
			   visitorsTmp.add(v);
			}
			for(int j=1;j<ConstantObject.visitorPageArray.length;j++) {
				if(p[j]==0) {
					VisitorPageMessage v = new VisitorPageMessage();
					   v.setNamePage(ConstantObject.visitorPageArray[j]);
					   v.setCount(0l);
					   visitorsTmp.add(v);
				}else {
					p[j]=0;
				}
			}
		visitorPage.setKeyname(key);	
		visitorPage.setValues(visitorsTmp);
		return visitorPage;
	}

}
