package com.xhu.entity;

import java.util.Date;

/**
 * Created by gp on 2018/3/21.
 */
public class Version {
    private Integer id;
    private String number;
    private String developer;
    private String uptime;

    public Version() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", developer='" + developer + '\'' +
                ", uptime=" + uptime +
                '}';
    }
}
