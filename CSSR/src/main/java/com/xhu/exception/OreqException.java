package com.xhu.exception;

import com.xhu.entity.CodeEnum;

/**
 * Created by gp on 2018/4/4.
 */
public class OreqException extends RuntimeException{
    private String code;
    private String mes;
    public OreqException() {
        super();
    }

    public OreqException(CodeEnum codeEnum) {
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

    public OreqException(String message) {
        super(message);
    }

    public OreqException(String message, Throwable cause) {
        super(message, cause);
    }

    public OreqException(Throwable cause) {
        super(cause);
    }

    protected OreqException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
