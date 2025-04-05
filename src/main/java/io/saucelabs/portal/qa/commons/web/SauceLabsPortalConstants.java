package io.saucelabs.portal.qa.commons.web;

import io.saucelabs.portal.qa.utils.ConfigLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SauceLabsPortalConstants {

    public static final String SAUCELABS_PORTAL_PROPERTIES_FILE = System.getProperty("user.dir")
            + "/src/main/java/resources/config.properties";
    public static final String MAXIMIZE = "--start-maximized";
    public static final String DIRECTORY = System.getProperty("user.dir") + "/src/test/java/screenshots";
    public static final String PASS = "/passed_screenshots/";

    public static final String PASS_PREFIX = "PASS_";
    public static final String FAIL = "/failed_screenshots/";
    public static final String FAIL_PREFIX = "FAILED_";
    public static final String IMAGE_FORMAT = ".png";

    public static final ConfigLoader CONFIG_LOADER = ConfigLoader.getInstance();
    public static final String RUN_MODE = "runmode";
    public static final String BROWSER = "browser";
    public static final String REMOTE = "remote";
    public static final String HEADLESS = "headless";
    public static final String HEADLESS_ARGUMENT = "--headless";

    // DISCORD API
    public static final String DISCORD_WEBHOOK = ConfigLoader.getInstance().getDiscordUrl() + "/api/webhooks";
}