package com.xhu.exception;

import com.xhu.entity.CodeEnum;

/**
 * Created by gp on 2018/5/25.
 */
public class RreqException extends RuntimeException{
    private String code;
    private String mes;
    public RreqException() {
        super();
    }

    public RreqException(CodeEnum codeEnum) {
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

    public RreqException(String message) {
        super(message);
    }

    public RreqException(String message, Throwable cause) {
        super(message, cause);
    }

    public RreqException(Throwable cause) {
        super(cause);
    }

    protected RreqException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
