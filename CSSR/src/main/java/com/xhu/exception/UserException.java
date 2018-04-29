package com.xhu.exception;

import com.xhu.entity.CodeEnum;

/**
 * Created by gp on 2018/3/21.
 */
public class UserException extends RuntimeException{
    private String code;
    private String mes;

    public UserException(CodeEnum codeEnum) {
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

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    protected UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
