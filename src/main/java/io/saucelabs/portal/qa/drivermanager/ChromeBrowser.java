package io.saucelabs.portal.qa.drivermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.exceptions.WebUtilsException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeBrowser implements IBrowser {

    @Override
    public WebDriver createLocalBrowserSession() {
        return new ChromeDriver();
    }

    @Override
    public WebDriver createHeadlessBrowserSession() {
        return new ChromeDriver(new ChromeOptions().addArguments(SauceLabsPortalConstants.HEADLESS_ARGUMENT));
    }

    @Override
    public WebDriver createRemoteBrowserSession(String remoteUrl) {
        try {
            return new RemoteWebDriver(new URL(remoteUrl), new ChromeOptions());
        } catch (MalformedURLException e) {
            throw new WebUtilsException("Chrome: Invalid remote URL: %s, message: %s".formatted(remoteUrl, e.getLocalizedMessage()));
        }
    }
}