package com.xhu.entity;

import java.util.Date;

/**
 * Created by gp on 2018/3/21.
 */
public class Message {
    private String mesId;
    private String mesContent;
    private String mesState;
    private Integer companyId;
    private String createTime;
    private String fromTime = "19900101000000";
    private String toTime = "20990101000000";

    public Message(Integer companyId) {
        this.companyId = companyId;
    }

    public Message() {
    }

    public String getMesId() {
        return mesId;
    }

    public void setMesId(String mesId) {
        this.mesId = mesId;
    }

    public String getMesContent() {
        return mesContent;
    }

    public void setMesContent(String mesContent) {
        this.mesContent = mesContent;
    }

    public String getMesState() {
        return mesState;
    }

    public void setMesState(String mesState) {
        this.mesState = mesState;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        return "Message{" +
                "mesId='" + mesId + '\'' +
                ", mesContent='" + mesContent + '\'' +
                ", mesState='" + mesState + '\'' +
                ", companyId=" + companyId +
                ", createTime=" + createTime +
                '}';
    }
}
