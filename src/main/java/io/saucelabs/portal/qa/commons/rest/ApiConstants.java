package io.saucelabs.portal.qa.commons.rest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConstants {
    public static final String API_PROPERTY_FILE = System.getProperty("user.dir") + "/src/main/java/resource/api.properties";
    public static final String WEBHOOK_TOKEN = "{your_web_hook_token}";
    public static final String CONTENT_TYPE = "application/json";
}