package com.xhu.exception;

import com.xhu.entity.CodeEnum;

/**
 * Created by gp on 2018/4/23.
 */
public class SreqException extends RuntimeException{

    private String code;
    private String mes;

    public SreqException(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.mes = codeEnum.getMessage();
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

    public SreqException() {
        super();
    }

    public SreqException(String message) {
        super(message);
    }

    public SreqException(String message, Throwable cause) {
        super(message, cause);
    }

    public SreqException(Throwable cause) {
        super(cause);
    }

    protected SreqException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
