package io.saucelabs.portal.qa.browsermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.exceptions.WebUtilsException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class MsEdgeBrowser implements IBrowser {

    @Override
    public WebDriver createLocalBrowserSession() {
        return new EdgeDriver();
    }

    @Override
    public WebDriver createHeadlessBrowserSession() {
        return new EdgeDriver(new EdgeOptions().addArguments(SauceLabsPortalConstants.HEADLESS_ARGUMENT));
    }

    @Override
    public WebDriver createRemoteBrowserSession(String remoteUrl) {
        try {
            return new RemoteWebDriver(new URL(remoteUrl), new EdgeOptions());
        } catch (MalformedURLException e) {
            throw new WebUtilsException("Edge: Invalid remote URL: %s, message: %s".formatted(remoteUrl, e.getLocalizedMessage()));
        }
    }
}