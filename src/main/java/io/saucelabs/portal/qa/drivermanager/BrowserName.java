package io.saucelabs.portal.qa.drivermanager;

import io.saucelabs.portal.qa.exceptions.TestUtilsException;
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

    public static BrowserName fromString(String browserName) {
        return Arrays.stream(BrowserName.values())
                .filter(browserType -> browserType.getBrowserType().equalsIgnoreCase(browserName))
                .findFirst().orElseThrow(() -> new TestUtilsException("Unknown browser: " + browserName));
    }
}
