package io.saucelabs.portal.qa.browsermanager;

import io.saucelabs.portal.qa.exceptions.WebUtilsException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BrowserName {

    CHROME("chrome"),
    FIREFOX("firefox"),
    MS_EDGE("msedge");

    private final String browserType;

    public static BrowserName getBrowserName(String browserName) {
        return Arrays.stream(BrowserName.values())
                .filter(browser -> browser.getBrowserType().equalsIgnoreCase(browserName))
                .findFirst().orElseThrow(() -> new WebUtilsException("Browser not supported: %s".formatted(browserName)));
    }
}