package com.weigo.commons.pojo;


public class VisitorPageMessage {
	private String namePage;
	private Long count;

	public String getNamePage() {
		return namePage;
	}
	public void setNamePage(String namePage) {
		this.namePage = namePage;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "VisitorPageMessage [namePage=" + namePage + ", count=" + count + "]";
	}
    
}
