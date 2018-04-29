package com.xhu.entity;

import java.util.Date;

/**
 * Created by gp on 2018/3/8.
 */
public class Manage {
    private Integer manageId;
    private String manageName;
    private String manageMc;
    private String managePwd;
    private String managePhone;
    private String manageEmail;
    private Integer manageRole;
    private String createTime;
    private String lastLoginTime;
    private Integer companyId;

    private String companyName;

    public Manage() {
    }


    public String getManageMc() {
        return manageMc;
    }

    public void setManageMc(String manageMc) {
        this.manageMc = manageMc;
    }

    public Integer getManageId() {
        return manageId;
    }

    public void setManageId(Integer manageId) {
        this.manageId = manageId;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public String getManagePwd() {
        return managePwd;
    }

    public void setManagePwd(String managePwd) {
        this.managePwd = managePwd;
    }

    public String getManagePhone() {
        return managePhone;
    }

    public void setManagePhone(String managePhone) {
        this.managePhone = managePhone;
    }

    public String getManageEmail() {
        return manageEmail;
    }

    public void setManageEmail(String manageEmail) {
        this.manageEmail = manageEmail;
    }

    public Integer getManageRole() {
        return manageRole;
    }

    public void setManageRole(Integer manageRole) {
        this.manageRole = manageRole;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Manage{" +
                "manageId=" + manageId +
                ", manageName='" + manageName + '\'' +
                ", manageMc='" + manageMc + '\'' +
                ", managePwd='" + managePwd + '\'' +
                ", managePhone='" + managePhone + '\'' +
                ", manageEmail='" + manageEmail + '\'' +
                ", manageRole=" + manageRole +
                ", createTime='" + createTime + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
