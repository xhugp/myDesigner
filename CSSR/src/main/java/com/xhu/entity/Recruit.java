package com.xhu.entity;


/**
 * Created by gp on 2018/5/22.
 */
public class Recruit {
    private Integer recruitId;
    private Integer recruitNumber;
    private String endTime;
    private String createTime;
    private Integer companyId;
    private Integer recruitType;
    private String recruitRemark;

    private String time;
    private String typeName;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public Integer getRecruitNumber() {
        return recruitNumber;
    }

    public void setRecruitNumber(Integer recruitNumber) {
        this.recruitNumber = recruitNumber;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getRecruitType() {
        return recruitType;
    }

    public void setRecruitType(Integer recruitType) {
        this.recruitType = recruitType;
    }

    public String getRecruitRemark() {
        return recruitRemark;
    }

    public void setRecruitRemark(String recruitRemark) {
        this.recruitRemark = recruitRemark;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Recruit{" +
                "recruitId=" + recruitId +
                ", recruitNumber=" + recruitNumber +
                ", endTime='" + endTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", companyId=" + companyId +
                ", recruitType=" + recruitType +
                ", recruitRemark='" + recruitRemark + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
