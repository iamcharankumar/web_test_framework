package io.saucelabs.portal.qa.commons.web;

import io.saucelabs.portal.qa.utils.WebConfigLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SauceLabsPortalConstants {

    public static final WebConfigLoader CONFIG_LOADER = WebConfigLoader.getInstance();
    public static final String HEADLESS_ARGUMENT = "--headless";
    public static final String BROWSER_NAME = System.getProperty("browser");
    public static final String RUN_MODE = System.getProperty("runmode");
}