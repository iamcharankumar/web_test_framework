package io.saucelabs.portal.qa.drivermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.utils.ConfigLoader;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
@NoArgsConstructor
public class WebDriverManager implements DriverManager<WebDriver> {

    @Override
    public WebDriver getDriver() {
        String runMode = System.getProperty(SauceLabsPortalConstants.RUN_MODE);
        String server = ConfigLoader.getInstance().getServerUrl();
        String browserName = System.getProperty(SauceLabsPortalConstants.BROWSER);
        WebDriverFactory webDriverFactory = new WebDriverFactory(browserName);
        if (runMode.equalsIgnoreCase(SauceLabsPortalConstants.REMOTE))
            return webDriverFactory.createRemoteBrowserSession(server);
        else
            return webDriverFactory.createLocalBrowserSession();
    }

    @Override
    public WebDriver getDriver(MutableCapabilities capabilities) {
        ChromeOptions chromeOptions = (ChromeOptions) capabilities;
        return new ChromeDriver(chromeOptions);
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