package com.weigo.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbEvaluate {
    private Long id;

    private Long uid;

    private String evaluatemsg;

    private Long evaluatescore;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getEvaluatemsg() {
        return evaluatemsg;
    }

    public void setEvaluatemsg(String evaluatemsg) {
        this.evaluatemsg = evaluatemsg;
    }

    public Long getEvaluatescore() {
        return evaluatescore;
    }

    public void setEvaluatescore(Long evaluatescore) {
        this.evaluatescore = evaluatescore;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}