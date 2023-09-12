package io.saucelabs.portal.qa.exceptions;

public class UnSupportedBrowserException extends RuntimeException {
    public UnSupportedBrowserException(String message) {
        super(message);
    }
}
