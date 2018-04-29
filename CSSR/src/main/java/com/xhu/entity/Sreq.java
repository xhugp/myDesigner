package com.xhu.entity;

/**
 * Created by gp on 2018/4/23.
 */
public class Sreq {
    private Integer srId;
    private Integer userId;
    private Integer companyId;
    private Integer srState;
    private Integer srTime;
    private String createTime;
    private String handleTime;


    private String userMc;
    private String companyName;
    private String state;

    private String fromTime =  "19900101000000";
    private String toTime = "20990101000000";

    public Integer getSrId() {
        return srId;
    }

    public void setSrId(Integer srId) {
        this.srId = srId;
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


    public Integer getSrState() {
        return srState;
    }

    public void setSrState(Integer srState) {
        this.srState = srState;
        if(srState == 0){
            this.state = "待处理";
        }
        else if(srState == 1){
            this.state = "签约成功";
        }
        else if(srState == 2){
            this.state = "被拒绝";
        }else{
            this.state = "";
        }
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getUserMc() {
        return userMc;
    }

    public void setUserMc(String userMc) {
        this.userMc = userMc;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Integer getSrTime() {
        return srTime;
    }

    public void setSrTime(Integer srTime) {
        this.srTime = srTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Sreq{" +
                "srId=" + srId +
                ", userId=" + userId +
                ", companyId=" + companyId +
                ", srState=" + srState +
                ", srTime=" + srTime +
                ", createTime='" + createTime + '\'' +
                ", handleTime='" + handleTime + '\'' +
                ", userMc='" + userMc + '\'' +
                ", companyName='" + companyName + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                '}';
    }
}
