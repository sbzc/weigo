package com.weigo.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbOrderItem {
    private String id;

    private Long orderId;

    private Long clientId;

    private Long sellerId;

    private Long itemId;

    private Integer num;

    private String title;

    private Long price;

    private Long totalFee;

    private String picPath;

    private Integer buyerRate;

    private Integer clientIsread;

    private Integer sellerIsread;

    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT-8")
    private Date updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Integer getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }

    public Integer getClientIsread() {
        return clientIsread;
    }

    public void setClientIsread(Integer clientIsread) {
        this.clientIsread = clientIsread;
    }

    public Integer getSellerIsread() {
        return sellerIsread;
    }

    public void setSellerIsread(Integer sellerIsread) {
        this.sellerIsread = sellerIsread;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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