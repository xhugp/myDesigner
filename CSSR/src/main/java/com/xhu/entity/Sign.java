package com.xhu.entity;

/**
 * Created by gp on 2018/3/28.
 */
public class Sign {
    private Integer userId;
    private Integer companyId;
    private String signTime;
    private String signDate;
    private String endDate;

    private String companyName;
    private String userMc;

    private String fromTime = "19900101000000";
    private String toTime = "20990101000000";

    public Sign(Integer companyId) {
        this.companyId = companyId;
    }

    public Sign() {
    }

    public Sign(Integer userId, Integer companyId, String signTime) {
        this.userId = userId;
        this.companyId = companyId;
        this.signTime = signTime;
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

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Sign{" +
                "userId=" + userId +
                ", companyId=" + companyId +
                ", signTime='" + signTime + '\'' +
                ", signDate='" + signDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", companyName='" + companyName + '\'' +
                ", userMc='" + userMc + '\'' +
                ", fromTime='" + fromTime + '\'' +
                ", toTime='" + toTime + '\'' +
                '}';
    }
}
