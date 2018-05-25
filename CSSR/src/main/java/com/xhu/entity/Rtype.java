package com.xhu.entity;

/**
 * Created by gp on 2018/5/22.
 */
public class Rtype {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rtype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
