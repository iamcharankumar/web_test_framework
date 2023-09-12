package io.saucelabs.portal.qa.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SauceLabsPortalConstant {

    public static final String SAUCELABS_PROTAL_PROPERTIES_FILE = System.getProperty("user.dir")
            + "/src/main/java/resources/config.properties";
    public static final String SAUCELABS_URL = "saucelabs.url";
    public static final String USER_NAME = "username";
    public static final String PASSWORD = "password";

    public static final String DIRECTORY = System.getProperty("user.dir") + "/src/test/java/screenshots";
    public static final String PASS = "/passed_screenshots/";

    public static final String PASS_PREFIX = "PASS_";
    public static final String FAIL = "/failed_screenshots/";
    public static final String FAIL_PREFIX = "FAILED_";
    public static final String IMAGE_FORMAT = ".png";

}