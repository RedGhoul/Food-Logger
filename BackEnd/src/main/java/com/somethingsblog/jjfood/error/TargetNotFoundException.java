package com.somethingsblog.jjfood.error;

public class TargetNotFoundException extends Exception{
    public TargetNotFoundException() {
        super();
    }

    public TargetNotFoundException(String message) {
        super(message);
    }

    public TargetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TargetNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TargetNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
