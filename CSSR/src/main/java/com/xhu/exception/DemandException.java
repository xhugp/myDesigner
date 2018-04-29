package com.xhu.exception;

import com.xhu.entity.CodeEnum;

/**
 * Created by gp on 2018/3/15.
 */
public class DemandException extends RuntimeException{
    private String code;
    private String mes;

    public DemandException(CodeEnum codeEnum) {
        code = codeEnum.getCode();
        mes = codeEnum.getMessage();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public DemandException() {
        super();
    }

    public DemandException(String message) {
        super(message);
    }

    public DemandException(String message, Throwable cause) {
        super(message, cause);
    }

    public DemandException(Throwable cause) {
        super(cause);
    }

    protected DemandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
