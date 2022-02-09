package com.gocartpay.exceptions;

/**
 * Generic GoCart exception
 */
public class GoCartException extends RuntimeException {
    public GoCartException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public GoCartException(Throwable e) {
        super(e);
    }

    public GoCartException(String errorMessage) {
        super(errorMessage);
    }
}
