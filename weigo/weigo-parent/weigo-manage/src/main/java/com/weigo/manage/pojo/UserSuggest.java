package com.weigo.manage.pojo;

import java.util.List;

public class UserSuggest {
       private String message;
       private List<UserSuggestData> value;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<UserSuggestData> getValue() {
		return value;
	}
	public void setValue(List<UserSuggestData> value) {
		this.value = value;
	}
       
}
