package com.xhu.entity;

/**
 * Created by gp on 2018/5/22.
 */
public class Rreq {
    private Integer rreqId;
    private Integer userId;
    private Integer companyId;
    private Integer recruitId;
    private Integer rreqState;
    private String rreqContent;
    private String createTime;

    private String userMc;
    private String recruitName;
    private String state;

    private String fromTime =  "19900101000000";
    private String toTime = "20990101000000";

    public Integer getRreqId() {
        return rreqId;
    }

    public void setRreqId(Integer rreqId) {
        this.rreqId = rreqId;
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

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public String getRreqContent() {
        return rreqContent;
    }

    public void setRreqContent(String rreqContent) {
        this.rreqContent = rreqContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserMc() {
        return userMc;
    }

    public void setUserMc(String userMc) {
        this.userMc = userMc;
    }

    public String getRecruitName() {
        return recruitName;
    }

    public void setRecruitName(String recruitName) {
        this.recruitName = recruitName;
    }

    public Integer getRreqState() {
        return rreqState;
    }

    public void setRreqState(Integer rreqState) {
        if(rreqState == 0){
            this.state = "未处理";
        }
        else if(rreqState == 1){
            this.state = "已处理";
        }else {
            this.state = "";
        }
        this.rreqState = rreqState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return "Rreq{" +
                "rreqId=" + rreqId +
                ", userId=" + userId +
                ", companyId=" + companyId +
                ", recruitId=" + recruitId +
                ", rreqState=" + rreqState +
                ", rreqContent='" + rreqContent + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userMc='" + userMc + '\'' +
                ", recruitName='" + recruitName + '\'' +
                ", state='" + state + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                '}';
    }
}
