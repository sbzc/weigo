package com.weigo.portal.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.weigo.pojo.TbItem;
import com.weigo.pojo.TbItemCat;

public class PortalMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TbItemCat> cats;
	private List<TbItem> items;
	private List<Map<String,Object>> bigPics;
	private List<Map<String,Object>> bigPicBolws;
	private List<Map<String,Object>> hostItemBolws;
	
	public List<Map<String, Object>> getBigPicBolws() {
		return bigPicBolws;
	}
	public void setBigPicBolws(List<Map<String, Object>> bigPicBolws) {
		this.bigPicBolws = bigPicBolws;
	}
	public List<Map<String, Object>> getHostItemBolws() {
		return hostItemBolws;
	}
	public void setHostItemBolws(List<Map<String, Object>> hostItemBolws) {
		this.hostItemBolws = hostItemBolws;
	}
	public List<TbItemCat> getCats() {
		return cats;
	}
	public void setCats(List<TbItemCat> cats) {
		this.cats = cats;
	}
	public List<TbItem> getItems() {
		return items;
	}
	public void setItems(List<TbItem> items) {
		this.items = items;
	}
	public List<Map<String, Object>> getBigPics() {
		return bigPics;
	}
	public void setBigPics(List<Map<String, Object>> bigPics) {
		this.bigPics = bigPics;
	}
	
	
}
