package com.weigo.dubbo.visitor.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.dubbo.visitor.service.TbVisitorDubboService;
import com.weigo.mapper.TbVisitorMapper;
import com.weigo.pojo.TbVisitor;
import com.weigo.pojo.TbVisitorExample;
@Service
public class TbVisitorDubboServiceImpl implements TbVisitorDubboService {

	@Autowired
	private TbVisitorMapper tbVisitorMapper;
	@Override
	public void insertVisitorByTbVisitor(TbVisitor tbVisitor) {
		try {
			tbVisitorMapper.insert(tbVisitor);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@Override
	public Long selectVisitorByVisitorWhart(Integer what) {
		TbVisitorExample example =new TbVisitorExample();
		example.createCriteria().andVisitorWhatEqualTo(what);
		
		return tbVisitorMapper.countByExample(example );
	}
	@Override
	public List<Map<String, Object>> selectByGroup(Date date) {
		
		TbVisitorExample example = new TbVisitorExample();
		if(date!=null) {
			example.createCriteria().andUpdatedEqualTo(date);
		}
		return tbVisitorMapper.selectByGroup(example );
	}

}
