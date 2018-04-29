package com.xhu.entity;

import java.util.Date;

/**
 * 汽修厂需求类
 * Created by gp on 2018/3/11.
 */
public class Demand {
    private String demandId;
    private Integer demandType;
    private String demandTitle;
    private String demandContent;
    private String demandRemark;
    private Float demandRepay;
    private String demandState;
    private Integer CompanyId;
    private String createTime;
    private String lastUpdateTime;
    private String typeName;
    private String fromTime = "19900101000000";
    private String toTime = "20990101000000";

    public Demand() {
    }

    public Demand(Integer companyId) {
        CompanyId = companyId;
    }

    public Demand(Integer demandType, String demandTitle, String demandContent, String demandRemark, Float demandRepay, String demandState, Integer companyId, String createTime, String lastUpdateTime) {
        this.demandType = demandType;
        this.demandTitle = demandTitle;
        this.demandContent = demandContent;
        this.demandRemark = demandRemark;
        this.demandRepay = demandRepay;
        this.demandState = demandState;
        CompanyId = companyId;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
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

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public void setDemandRepay(Float demandRepay) {
        this.demandRepay = demandRepay;
    }

    public String getDemandTitle() {
        return demandTitle;
    }

    public void setDemandTitle(String demandTitle) {
        this.demandTitle = demandTitle;
    }


    public Integer getDemandType() {
        return demandType;
    }

    public void setDemandType(Integer demandType) {
        this.demandType = demandType;
    }

    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }

    public String getDemandContent() {
        return demandContent;
    }

    public void setDemandContent(String demandContent) {
        this.demandContent = demandContent;
    }

    public String getDemandRemark() {
        return demandRemark;
    }

    public void setDemandRemark(String demandRemark) {
        this.demandRemark = demandRemark;
    }

    public Float getDemandRepay() {
        return demandRepay;
    }

    public String getDemandState() {
        return demandState;
    }

    public void setDemandState(String demandState) {
        this.demandState = demandState;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "demandId='" + demandId + '\'' +
                ", demandType=" + demandType +
                ", demandTitle='" + demandTitle + '\'' +
                ", demandContent='" + demandContent + '\'' +
                ", demandRemark='" + demandRemark + '\'' +
                ", demandRepay=" + demandRepay +
                ", demandState='" + demandState + '\'' +
                ", CompanyId=" + CompanyId +
                ", createTime='" + createTime + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", typeName='" + typeName + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                '}';
    }
}
