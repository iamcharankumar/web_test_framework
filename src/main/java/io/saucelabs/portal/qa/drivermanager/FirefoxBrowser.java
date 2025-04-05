package io.saucelabs.portal.qa.drivermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.exceptions.WebUtilsException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxBrowser implements IBrowser {

    @Override
    public WebDriver createLocalBrowserSession() {
        return new FirefoxDriver();
    }

    @Override
    public WebDriver createHeadlessBrowserSession() {
        return new FirefoxDriver(new FirefoxOptions().addArguments(SauceLabsPortalConstants.HEADLESS_ARGUMENT));
    }

    @Override
    public WebDriver createRemoteBrowserSession(String remoteUrl) {
        try {
            return new RemoteWebDriver(new URL(remoteUrl), new FirefoxOptions());
        } catch (MalformedURLException e) {
            throw new WebUtilsException("Firefox: Invalid remote URL: %s, message: %s".formatted(remoteUrl, e.getLocalizedMessage()));
        }
    }
}