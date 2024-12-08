package io.saucelabs.portal.qa.drivermanager;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class FirefoxBrowser implements Browser {

    @Override
    public WebDriver createWebDriver() {
        return new FirefoxDriver();
    }

    @Override
    public WebDriver createRemoteWebDriver(String url) {
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), new FirefoxOptions());
        } catch (MalformedURLException e) {
            log.error("Malformed Remote URL! {}", e.getLocalizedMessage());
        }
        return driver;
    }
}