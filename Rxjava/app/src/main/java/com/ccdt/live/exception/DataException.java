package com.ccdt.live.exception;

/**
 * Created by rain on 2016/1/4.
 */
public class DataException extends Exception {
    private static final long serialVersionUID = -6031863210486494461L;

    public DataException() {
    }

    public DataException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DataException(String detailMessage) {
        super(detailMessage);
    }

    public DataException(Throwable throwable) {
        super(throwable);
    }
}
