package io.saucelabs.portal.qa.drivermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
@NoArgsConstructor
public class WebDriverManager implements DriverManager<WebDriver> {

    @Override
    public WebDriver getDriver() {
        String browserName = System.getProperty(SauceLabsPortalConstants.BROWSER);
        BrowserFactory browserFactory = new BrowserFactory();
        return switch (System.getProperty(SauceLabsPortalConstants.RUN_MODE)) {
            case SauceLabsPortalConstants.REMOTE -> browserFactory.createBrowser(browserName)
                    .createRemoteBrowserSession(SauceLabsPortalConstants.CONFIG_LOADER.getServerUrl());
            case SauceLabsPortalConstants.HEADLESS ->
                    browserFactory.createBrowser(browserName).createHeadlessBrowserSession();
            default -> browserFactory.createBrowser(browserName).createLocalBrowserSession();
        };
    }

    @Override
    public void destroyDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        } else {
            log.error("Browser is not yet initialized");
        }
    }
}