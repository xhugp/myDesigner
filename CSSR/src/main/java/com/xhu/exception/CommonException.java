package com.xhu.exception;

/**
 * Created by gp on 2018/3/10.
 */
public class CommonException extends RuntimeException {
        public CommonException() {
        }

        public CommonException(String message) {
            super(message);
        }

        public CommonException(Throwable cause) {
            super(cause);
        }

        public CommonException(String message, Throwable cause) {
            super(message, cause);
        }

        public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }


}
