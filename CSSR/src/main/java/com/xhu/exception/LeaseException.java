package com.xhu.exception;

/**
 * Created by gp on 2018/3/22.
 */
public class LeaseException extends RuntimeException {
    public LeaseException() {
        super();
    }

    public LeaseException(String message) {
        super(message);
    }

    public LeaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeaseException(Throwable cause) {
        super(cause);
    }

    protected LeaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
