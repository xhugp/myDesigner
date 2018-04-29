package com.xhu.entity;

/**
 * Created by gp on 2018/3/20.
 */
public class Dtype {
    private Integer id;
    private String name;

    public Dtype() {
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
        return "Dtype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
