package com.xhu.entity;

import java.util.Date;

/**
 * Created by gp on 2018/3/21.
 */
public class Lease {
    private String leaseId;
    private Integer userId;
    private Integer toolId;
    private String leaseMes;
    private Float leaseRepay;
    private String leaseTerm;
    private String leaseState;
    private Float totalMoney;
    private String createTime;
    private String endTime;

    private String userMc;
    private String toolName;
    private Integer companyId;
    private String state;

    private String fromTime = "19900101000000";
    private String toTime = "20990101000000";

    public Lease() {
    }

    public Lease(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Lease(Integer userId, Integer toolId, String leaseMes, Float leaseRepay, String leaseTerm, String leaseState, String createTime) {
        this.userId = userId;
        this.toolId = toolId;
        this.leaseMes = leaseMes;
        this.leaseRepay = leaseRepay;
        this.leaseTerm = leaseTerm;
        this.leaseState = leaseState;
        this.createTime = createTime;
    }

    public String getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(String leaseId) {
        this.leaseId = leaseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public String getLeaseMes() {
        return leaseMes;
    }

    public void setLeaseMes(String leaseMes) {
        this.leaseMes = leaseMes;
    }

    public Float getLeaseRepay() {
        return leaseRepay;
    }

    public void setLeaseRepay(Float leaseRepay) {
        this.leaseRepay = leaseRepay;
    }

    public String getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(String leaseTerm) {
        this.leaseTerm = leaseTerm;
    }

    public String getLeaseState() {
        return leaseState;
    }

    public void setLeaseState(String leaseState) {
        this.leaseState = leaseState;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "leaseId='" + leaseId + '\'' +
                ", userId=" + userId +
                ", toolId=" + toolId +
                ", leaseMes='" + leaseMes + '\'' +
                ", leaseRepay=" + leaseRepay +
                ", leaseTerm='" + leaseTerm + '\'' +
                ", leaseState='" + leaseState + '\'' +
                ", totalMoney=" + totalMoney +
                ", createTime='" + createTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", userMc='" + userMc + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                '}';
    }
}
