package com.gocart.exceptions;

/**
 * Exception thrown when a request is invalid
 */
public class InvalidRequestException extends GoCartException {

    public InvalidRequestException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public InvalidRequestException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidRequestException(Throwable throwable) {
        super(throwable);
    }
}
