package io.saucelabs.portal.qa.exceptions;

public class TestUtilsException extends RuntimeException {
    public TestUtilsException(String errorMessage) {
        super(errorMessage);
    }
}