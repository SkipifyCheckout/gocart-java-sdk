package com.gocart.exceptions;

/**
 * Exception thrown when the GoCartRestClient encounters issues
 */
public class GoCartClientException extends GoCartException {

    public GoCartClientException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public GoCartClientException(String errorMessage) {
        super(errorMessage);
    }

    public GoCartClientException(Throwable throwable) {
        super(throwable);
    }
}
