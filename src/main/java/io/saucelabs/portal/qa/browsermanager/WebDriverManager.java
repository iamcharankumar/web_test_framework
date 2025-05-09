package io.saucelabs.portal.qa.browsermanager;

import io.saucelabs.portal.qa.commons.WebConstants;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

@Slf4j
@NoArgsConstructor
public class WebDriverManager implements IDriverManager<WebDriver> {

    @Override
    public WebDriver getDriver() {
        BrowserFactory browserFactory = new BrowserFactory();
        String browserName = WebConstants.BROWSER_NAME;
        return switch (WebConstants.RUN_MODE) {
            case "remote" -> browserFactory.createBrowser(browserName)
                    .createRemoteBrowserSession(WebConstants.CONFIG_LOADER.getServerUrl());
            case "headless" -> browserFactory.createBrowser(browserName).createHeadlessBrowserSession();
            default -> browserFactory.createBrowser(browserName).createLocalBrowserSession();
        };
    }

    @Override
    public void destroyDriver(WebDriver driver) {
        Objects.requireNonNull(driver, "Browser is not yet initialized!");
        driver.quit();
    }
}