package com.gocartpay.exceptions;

/**
 * Exception thrown when the GoCart Api returns a bad response code
 */
public class GoCartApiException extends GoCartException {
    public GoCartApiException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public GoCartApiException(Throwable e) {
        super(e);
    }

    public GoCartApiException(String errorMessage) {
        super(errorMessage);
    }
}
