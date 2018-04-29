package com.xhu.entity;

/**
 * Created by gp on 2018/4/4.
 */
public class Oreq {
    private Integer oreqId;
    private Integer userId;
    private Integer companyId;
    private String orderId;
    private String oreqContent;
    private String oreqResult;
    private String oreqType;
    private String oreqCreateTime;
    private String oreqHandleTime;

    private String userMc;
    private String type;
    private String fromTime =  "19900101000000";
    private String toTime = "20990101000000";

    public Oreq() {
    }

    public Oreq(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getOreqId() {
        return oreqId;
    }

    public void setOreqId(Integer oreqId) {
        this.oreqId = oreqId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getOreqContent() {
        return oreqContent;
    }

    public void setOreqContent(String oreqContent) {
        this.oreqContent = oreqContent;
    }

    public String getOreqResult() {
        return oreqResult;
    }

    public void setOreqResult(String oreqResult) {
        this.oreqResult = oreqResult;
    }

    public String getOreqCreateTime() {
        return oreqCreateTime;
    }

    public void setOreqCreateTime(String oreqCreateTime) {
        this.oreqCreateTime = oreqCreateTime;
    }

    public String getOreqHandleTime() {
        return oreqHandleTime;
    }

    public void setOreqHandleTime(String oreqHandleTime) {
        this.oreqHandleTime = oreqHandleTime;
    }

    public String getUserMc() {
        return userMc;
    }

    public void setUserMc(String userMc) {
        this.userMc = userMc;
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

    public String getOreqType() {
        return oreqType;
    }

    public void setOreqType(String oreqType) {
        this.oreqType = oreqType;
        setType(oreqType);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if("1".equals(type)){
            this.type = "订单结束";
            return;
        }else if("2".equals(type)){
            this.type = "订单中断";
            return;
        }
        this.type = null;
    }

    @Override
    public String toString() {
        return "Oreq{" +
                "oreqId=" + oreqId +
                ", userId=" + userId +
                ", companyId=" + companyId +
                ", orderId='" + orderId + '\'' +
                ", oreqContent='" + oreqContent + '\'' +
                ", oreqResult='" + oreqResult + '\'' +
                ", oreqType='" + oreqType + '\'' +
                ", oreqCreateTime='" + oreqCreateTime + '\'' +
                ", oreqHandleTime='" + oreqHandleTime + '\'' +
                ", userMc='" + userMc + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                '}';
    }
}
