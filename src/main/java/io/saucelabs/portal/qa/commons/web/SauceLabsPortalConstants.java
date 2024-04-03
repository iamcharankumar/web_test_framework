package io.saucelabs.portal.qa.commons.web;

import io.saucelabs.portal.qa.utils.ConfigLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SauceLabsPortalConstants {

    public static final String SAUCELABS_PORTAL_PROPERTIES_FILE = System.getProperty("user.dir")
            + "/src/main/java/resources/config.properties";
    public static final String DIRECTORY = System.getProperty("user.dir") + "/src/test/java/screenshots";
    public static final String PASS = "/passed_screenshots/";

    public static final String PASS_PREFIX = "PASS_";
    public static final String FAIL = "/failed_screenshots/";
    public static final String FAIL_PREFIX = "FAILED_";
    public static final String IMAGE_FORMAT = ".png";

    // DISCORD API
    public static final String DISCORD_WEBHOOK = ConfigLoader.getInstance().getDiscordUrl() + "/api/webhooks";
}