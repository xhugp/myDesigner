package com.xhu.entity;

/**
 * Created by gp on 2018/3/29.
 */
public class LReq {
    private Integer lReqId;
    private Integer userId;
    private Integer companyId;
    private Integer toolId;

    private Float lReqMoney;
    private String lReqTerm;
    private String lReqContent;
    private String lReqResult;
    private String lReqCreateTime;
    private String lReqHandleTime;

    private String userMc;
    private String toolName;
    private String fromTime =  "19900101000000";
    private String toTime = "20990101000000";

    public Integer getlReqId() {
        return lReqId;
    }

    public void setlReqId(Integer lReqId) {
        this.lReqId = lReqId;
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

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public String getlReqTerm() {
        return lReqTerm;
    }

    public void setlReqTerm(String lReqTerm) {
        this.lReqTerm = lReqTerm;
    }

    public String getlReqContent() {
        return lReqContent;
    }

    public void setlReqContent(String lReqContent) {
        this.lReqContent = lReqContent;
    }

    public String getlReqResult() {
        return lReqResult;
    }

    public void setlReqResult(String lReqResult) {
        this.lReqResult = lReqResult;
    }

    public String getlReqCreateTime() {
        return lReqCreateTime;
    }

    public void setlReqCreateTime(String lReqCreateTime) {
        this.lReqCreateTime = lReqCreateTime;
    }

    public String getlReqHandleTime() {
        return lReqHandleTime;
    }

    public void setlReqHandleTime(String lReqHandleTime) {
        this.lReqHandleTime = lReqHandleTime;
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

    public String getUserMc() {
        return userMc;
    }

    public void setUserMc(String userMc) {
        this.userMc = userMc;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public Float getlReqMoney() {
        return lReqMoney;
    }

    public void setlReqMoney(Float lReqMoney) {
        this.lReqMoney = lReqMoney;
    }

    @Override
    public String toString() {
        return "LReq{" +
                "lReqId=" + lReqId +
                ", userId=" + userId +
                ", companyId=" + companyId +
                ", toolId=" + toolId +
                ", lReqMoney='" + lReqMoney + '\'' +
                ", lReqTerm='" + lReqTerm + '\'' +
                ", lReqContent='" + lReqContent + '\'' +
                ", lReqResult='" + lReqResult + '\'' +
                ", lReqCreateTime='" + lReqCreateTime + '\'' +
                ", lReqHandleTime='" + lReqHandleTime + '\'' +
                ", userMc='" + userMc + '\'' +
                ", toolName='" + toolName + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                '}';
    }
}
