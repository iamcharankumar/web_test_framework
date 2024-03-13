package io.saucelabs.portal.qa.drivermanager;

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
        String runMode = ConfigLoader.getInstance().getRunMode();
        String server = ConfigLoader.getInstance().getServerUrl();
        String browserName = ConfigLoader.getInstance().getBrowser();
        WebDriverFactory webDriverFactory = new WebDriverFactory(browserName);
        if (runMode.equalsIgnoreCase("remote")) {
            log.info("Executing the test cases in remote server {}", server);
            return webDriverFactory.createRemoteBrowserSession(server);
        } else {
            log.info("Executing the test cases in local machine.");
            return webDriverFactory.createLocalBrowserSession();
        }
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