package com.xhu.exception;

/**
 * Created by gp on 2018/3/22.
 */
public class ToolException extends RuntimeException {
    public ToolException() {
        super();
    }

    public ToolException(String message) {
        super(message);
    }

    public ToolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToolException(Throwable cause) {
        super(cause);
    }

    protected ToolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
