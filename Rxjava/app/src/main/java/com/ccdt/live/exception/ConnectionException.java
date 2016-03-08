package com.ccdt.live.exception;

/**
 * Created by rain on 2016/1/4.
 */
public class ConnectionException extends Exception {

    private static final long serialVersionUID = 4658308128254827562L;
    private String mRedirectionUrl;
    private int mStatusCode = -1;

    public ConnectionException() {
    }

    public ConnectionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ConnectionException(String detailMessage) {
        super(detailMessage);
    }

    public ConnectionException(String detailMessage, String redirectionUrl) {
        super(detailMessage);
        this.mRedirectionUrl = redirectionUrl;
        this.mStatusCode = 301;
    }

    public ConnectionException(String detailMessage, int statusCode) {
        super(detailMessage);
        this.mStatusCode = statusCode;
    }

    public ConnectionException(Throwable throwable) {
        super(throwable);
    }

    public String getRedirectionUrl() {
        return this.mRedirectionUrl;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }
}
