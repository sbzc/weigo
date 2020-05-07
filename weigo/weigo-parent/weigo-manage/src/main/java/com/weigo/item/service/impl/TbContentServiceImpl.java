package com.weigo.item.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.weigo.commons.pojo.EasyUIDataGrid;
import com.weigo.commons.pojo.MessageObject;
import com.weigo.dubbo.item.service.TbContentDubboService;
import com.weigo.item.service.TbContentService;
import com.weigo.pojo.TbContent;


@Service
public class TbContentServiceImpl implements TbContentService{
    
	
	@Autowired
	private TbContentDubboService tbContentDubboService;
	@Override
	public EasyUIDataGrid selectContent(Long categoryId, int page, int rows) {
		
		PageInfo<TbContent> info = tbContentDubboService.selectContentBycategoryId(categoryId,page,rows);
		EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
		
		easyUIDataGrid.setRows(info.getList());
		easyUIDataGrid.setTotal(info.getTotal());
		return easyUIDataGrid;
	}

	@Override
	public MessageObject insertContent(TbContent content) {
       
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		Date date = new Date();
	    content.setCreated(date);
	    content.setUpdated(date);
		int rows = tbContentDubboService.insertContent(content);
		if(rows==1) {
			mo.setCode(1);
			
		}
		return mo;
	}

	@Override
	public MessageObject deleteContentByIds(String ids) {
		
		MessageObject mo = new MessageObject();
		mo.setCode(0);
		int rows=0;
		try {
			rows = tbContentDubboService.deleteContentByIds(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return mo;
		}
		if(rows==1) {
			mo.setCode(1);
		}
		return mo;
	}

	@Override
	public MessageObject updateContentByContent(TbContent content) {
		int ok = tbContentDubboService.updateContentByContent(content);
		MessageObject mo = new MessageObject();
		mo.setCode(ok);
		mo.setMsg("修改成功");
		if(ok==0) {
			mo.setMsg("修改失败");
		}
		return mo;
	}

}
