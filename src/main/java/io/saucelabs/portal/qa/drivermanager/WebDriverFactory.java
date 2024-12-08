package io.saucelabs.portal.qa.drivermanager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WebDriverFactory {

    private final String browserName;

    public Browser createBrowser() {
        return switch (BrowserName.fromString(browserName)) {
            case CHROME -> new ChromeBrowser();
            case FIREFOX -> new FirefoxBrowser();
            case MS_EDGE -> new MsEdgeBrowser();
        };
    }
}