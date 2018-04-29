package com.xhu.entity;

/**
 * Created by gp on 2018/3/21.
 */
public class Ttype {
    private Integer id;
    private String name;

    public Ttype() {
    }

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
        return "Ttype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
