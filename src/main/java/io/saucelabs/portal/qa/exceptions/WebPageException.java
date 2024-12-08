package io.saucelabs.portal.qa.exceptions;

public class WebPageException extends RuntimeException {

    public WebPageException(String errorMessage) {
        super(errorMessage);
    }
}