package com.xhu.exception;

/**
 * Created by gp on 2018/3/20.
 */
public class CompanyException extends RuntimeException{
    public CompanyException() {
        super();
    }

    public CompanyException(String message) {
        super(message);
    }

    public CompanyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompanyException(Throwable cause) {
        super(cause);
    }

    protected CompanyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
