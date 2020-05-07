package com.weigo.commons.pojo;

public class UserMessage {
    private String username;
    private String rolename;
    private String phone;
    private Long upItemCount;
    private String email;
    private Long evaluateCount;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Long getUpItemCount() {
		return upItemCount;
	}
	public void setUpItemCount(Long upItemCount) {
		this.upItemCount = upItemCount;
	}
	public Long getEvaluateCount() {
		return evaluateCount;
	}
	public void setEvaluateCount(Long evaluateCount) {
		this.evaluateCount = evaluateCount;
	}
    
}
