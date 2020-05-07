package com.weigo.dubbo.shipping.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weigo.commons.pojo.ConstantObject;
import com.weigo.dubbo.shipping.service.TbOrderShippingDubboService;
import com.weigo.mapper.TbOrderItemMapper;
import com.weigo.mapper.TbOrderShippingMapper;
import com.weigo.pojo.TbOrderItem;
import com.weigo.pojo.TbOrderShipping;
import com.weigo.pojo.TbOrderShippingExample;
@Service
public class TbOrderShippingDubboServiceImpl implements TbOrderShippingDubboService {

	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Override
	public List<TbOrderShipping> selectOrderShippingByStatus(int status) {
		TbOrderShippingExample example  = new TbOrderShippingExample();
		example.createCriteria().andStatusEqualTo(status);
		example.setOrderByClause("updated desc");
		return tbOrderShippingMapper.selectByExample(example  );
	}

	@Override
	public int updateShippingAndOrderItem(String orderItemId, Long uid) throws Exception {
		Date date = new Date();
		TbOrderShipping shipping = tbOrderShippingMapper.selectByPrimaryKey(orderItemId);
		TbOrderItem orderItem = tbOrderItemMapper.selectByPrimaryKey(orderItemId);
		Integer status = shipping.getStatus();
		if(status!=ConstantObject.prepareSendOrderItem) {
			return ConstantObject.resetOrderItem;
		}
		shipping.setStatus(ConstantObject.SendOrderItem);
	
		shipping.setUpdated(date);
		shipping.setUid(uid);
		orderItem.setStatus(ConstantObject.SendOrderItem);
		orderItem.setUpdated(date);
		
		int row=0;
		row= tbOrderShippingMapper.updateByPrimaryKeySelective(shipping);
		row+=tbOrderItemMapper.updateByPrimaryKeySelective(orderItem);
		if(row==2) {
			return 1;
		}else {
			throw new Exception();
		}
		
	}


	@Override
	public List<TbOrderShipping> selectOrderShippingByUid(Long id) {
		TbOrderShippingExample example = new TbOrderShippingExample();
		example.createCriteria().andUidEqualTo(id);
		example.setOrderByClause("updated desc");
		return tbOrderShippingMapper.selectByExample(example);
	}

	@Override
	public TbOrderShipping selectOrderShippingByShippingId(String orderItemId) {
		
		return tbOrderShippingMapper.selectByPrimaryKey(orderItemId);
	}

	@Override
	public Long selectPendingOrderShippingByUid(Long id) {
		TbOrderShippingExample example = new TbOrderShippingExample();
		example.createCriteria().andUidEqualTo(id).andStatusEqualTo(ConstantObject.SendOrderItem);
		return this.tbOrderShippingMapper.countByExample(example);
	}

	

}
