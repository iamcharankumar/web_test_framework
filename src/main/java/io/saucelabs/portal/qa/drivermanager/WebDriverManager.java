package io.saucelabs.portal.qa.drivermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

@Slf4j
@NoArgsConstructor
public class WebDriverManager implements DriverManager<WebDriver> {

    @Override
    public WebDriver getDriver() {
        String runMode = System.getProperty(SauceLabsPortalConstants.RUN_MODE);
        String server = SauceLabsPortalConstants.CONFIG_LOADER.getServerUrl();
        String browserName = System.getProperty(SauceLabsPortalConstants.BROWSER);
        WebDriverFactory webDriverFactory = new WebDriverFactory(browserName);
        if (runMode.equalsIgnoreCase(SauceLabsPortalConstants.REMOTE))
            return webDriverFactory.createBrowser().createRemoteWebDriver(server);
        else
            return webDriverFactory.createBrowser().createWebDriver();
    }

    @Override
    public void destroyDriver(WebDriver driver) {
        Objects.requireNonNull(driver, "Browser is not yet initialized");
        driver.quit();
    }
}