package com.xhu.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by gp on 2018/3/20.
 */
public class Order {
    private String orderId;
    private String orderTitle;
    private Integer userId;
    private Float orderScore;
    private String demandId;
    private String orderState;
    private String createTime;
    private String lastUpdateTime;
    private String fromTime = "19900101000000";
    private String toTime = "20990101000000";

    private Integer companyId;

    private String userMc;
    private String state;


    public Order(String demandId) {
        this.demandId = demandId;
    }

    public Order() {
    }

    public Order(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getUserMc() {
        return userMc;
    }

    public void setUserMc(String userMc) {
        this.userMc = userMc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Float getOrderScore() {
        return orderScore;
    }

    public void setOrderScore(Float orderScore) {
        this.orderScore = orderScore;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderTitle='" + orderTitle + '\'' +
                ", userId=" + userId +
                ", orderScore=" + orderScore +
                ", demandId='" + demandId + '\'' +
                ", orderState='" + orderState + '\'' +
                ", createTime='" + createTime + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                ", companyId=" + companyId +
                ", userMc='" + userMc + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
