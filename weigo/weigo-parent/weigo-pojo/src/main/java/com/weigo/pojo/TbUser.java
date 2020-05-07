package com.weigo.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbUser implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String username;

    private String password;

    private String phone;

    private String salt;

    private Integer status;

    private Long roleId;

    private String email;

    private Integer score;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updated;
    //显示中文状态
   private String statusStr;
   //显示角色名
   private String rolename;

    public String getStatusStr() {
	return statusStr;
}

public void setStatusStr(String statusStr) {
	this.statusStr = statusStr;
}

public String getRolename() {
	return rolename;
}

public void setRolename(String rolename) {
	this.rolename = rolename;
}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}