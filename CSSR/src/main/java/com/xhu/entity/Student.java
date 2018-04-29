package com.xhu.entity;

import java.util.Date;

/**
 * Created by gp on 2018/2/25.
 */
public class Student {
    private int stuId;
    private String stuName;
    private int pxh;
    private Date createTime;
    private Date lastUpdateTime;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getPxh() {
        return pxh;
    }

    public void setPxh(int pxh) {
        this.pxh = pxh;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
