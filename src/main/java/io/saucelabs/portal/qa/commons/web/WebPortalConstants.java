package io.saucelabs.portal.qa.commons.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebPortalConstants {
    public static final String BROWSER_OPTIONS_HEADLESS_ARGUMENT = "--headless";
    public static final String BROWSER_OPTIONS_WINDOW_SIZE_ARGUMENT = "--window-size=1920,1200";
    public static final String BROWSER_OPTIONS_START_MAXIMIZE_ARGUMENT = "--start-maximized";
    public static final String BROWSER_OPTIONS_IGNORE_CERTS_ARGUMENT = "--ignore-certificate-errors";
    public static final String BROWSER_OPTIONS_DISABLE_EXTENSIONS_ARGUMENT = "--disable-extensions";
    public static final String BROWSER_OPTIONS_NO_SANDBOX_ARGUMENT = "--no-sandbox";
    public static final String BROWSER_OPTIONS_DISABLE_DEV_SHM_USAGE_ARGUMENT = "--disable-dev-shm-usage";
    public static final String BROWSER_OPTIONS_REMOTE_ALLOW_ORIGINS_ARGUMENT = "--remote-allow-origins=*";
    public static final Duration BROWSER_OPTIONS_IMPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(3);
}
