package com.weigo.order.pojo;

import java.util.List;

import com.weigo.pojo.TbOrderItem;
import com.weigo.pojo.TbOrderShipping;

public class MyOrderParam {
	//�ܽ��
	private String payment;
	//��Ʒ�ܺ�
	private List<TbOrderItem> orderItems;
	//�ջ���ַId
	private Long endId;
	
	public Long getEndId() {
		return endId;
	}
	public void setEndId(Long endId) {
		this.endId = endId;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
}
