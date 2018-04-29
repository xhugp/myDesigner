package com.xhu.dto;

/**
 * Created by gp on 2018/3/6.
 */
public class ResultBean<T> {
    private String code;
    private T data;
    private String msg;

    public ResultBean(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ResultBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
