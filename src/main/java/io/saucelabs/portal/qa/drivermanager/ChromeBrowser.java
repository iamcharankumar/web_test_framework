package io.saucelabs.portal.qa.drivermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Slf4j
public class ChromeBrowser implements Browser {

    @Override
    public WebDriver createWebDriver() {
        return new ChromeDriver();
    }

    @Override
    public WebDriver createRemoteWebDriver(String url) {
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), getChromeOptions());
        } catch (MalformedURLException e) {
            log.error("Malformed Remote URL! {}", e.getLocalizedMessage());
        }
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.setImplicitWaitTimeout(Duration.ofSeconds(5));
        options.addArguments(SauceLabsPortalConstants.MAXIMIZE);
        return options;
    }
}
