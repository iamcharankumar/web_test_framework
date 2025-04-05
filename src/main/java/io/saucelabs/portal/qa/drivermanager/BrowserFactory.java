package io.saucelabs.portal.qa.drivermanager;

public class BrowserFactory {

    public IBrowser createBrowser(String browserName) {
        return switch (BrowserName.getBrowserName(browserName)) {
            case FIREFOX -> new FirefoxBrowser();
            case MS_EDGE -> new MsEdgeBrowser();
            default -> new ChromeBrowser();
        };
    }
}