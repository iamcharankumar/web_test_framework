package io.saucelabs.portal.qa.exceptions;

public class RestResourceException extends RuntimeException {
    public RestResourceException(String errorMessage) {
        super(errorMessage);
    }
}