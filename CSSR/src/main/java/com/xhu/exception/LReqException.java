package com.xhu.exception;

import com.xhu.entity.CodeEnum;

/**
 * Created by gp on 2018/3/30.
 */
public class LReqException extends RuntimeException {
    private String code;
    private String mes;
    public LReqException() {
        super();
    }

    public LReqException(CodeEnum codeEnum) {
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

    public LReqException(String message) {
        super(message);
    }

    public LReqException(String message, Throwable cause) {
        super(message, cause);
    }

    public LReqException(Throwable cause) {
        super(cause);
    }

    protected LReqException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
