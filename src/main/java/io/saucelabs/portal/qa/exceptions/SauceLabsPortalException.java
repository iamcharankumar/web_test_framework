package io.saucelabs.portal.qa.exceptions;

public class SauceLabsPortalException extends RuntimeException {
    public SauceLabsPortalException(String errorMessage) {
        super(errorMessage);
    }
}