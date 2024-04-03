package io.saucelabs.portal.qa.exceptions;

public class DiscordException extends RuntimeException {
    public DiscordException(String errorMessage) {
        super(errorMessage);
    }
}