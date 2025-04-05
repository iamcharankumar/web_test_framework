package io.saucelabs.portal.qa.browsermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
@NoArgsConstructor
public class WebDriverManager implements DriverManager<WebDriver> {

    @Override
    public WebDriver getDriver() {
        BrowserFactory browserFactory = new BrowserFactory();
        String browserName = System.getProperty("browser");
        return switch (System.getProperty("runmode")) {
            case "remote" -> browserFactory.createBrowser(browserName)
                    .createRemoteBrowserSession(SauceLabsPortalConstants.CONFIG_LOADER.getServerUrl());
            case "headless" -> browserFactory.createBrowser(browserName).createHeadlessBrowserSession();
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