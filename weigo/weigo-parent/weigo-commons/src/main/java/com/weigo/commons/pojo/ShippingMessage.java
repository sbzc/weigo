package com.weigo.commons.pojo;

import java.util.Date;

public class ShippingMessage {
  
	private Date updated;
	private String startName;
	private String endName;
	private String startPhone;
	private String endPhone;
	private String startAddress;
	private String endAddress;
	private String orderItemId;
	private String image;
	private String itemTitle;
	private Long itemId;
	private Long privce;
	
	public Long getPrivce() {
		return privce;
	}
	public void setPrivce(Long privce) {
		this.privce = privce;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getStartName() {
		return startName;
	}
	public void setStartName(String startName) {
		this.startName = startName;
	}
	public String getEndName() {
		return endName;
	}
	public void setEndName(String endName) {
		this.endName = endName;
	}
	public String getStartPhone() {
		return startPhone;
	}
	public void setStartPhone(String startPhone) {
		this.startPhone = startPhone;
	}
	public String getEndPhone() {
		return endPhone;
	}
	public void setEndPhone(String endPhone) {
		this.endPhone = endPhone;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
	
}
