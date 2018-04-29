package com.xhu.exception;

/**
 * Created by gp on 2018/3/8.
 */
public class ManageException extends RuntimeException{
    public ManageException() {
    }

    public ManageException(String message) {
        super(message);
    }

    public ManageException(Throwable cause) {
        super(cause);
    }

    public ManageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
