package io.saucelabs.portal.qa.drivermanager;

import org.openqa.selenium.WebDriver;

public interface IBrowser {

    WebDriver createLocalBrowserSession();

    WebDriver createHeadlessBrowserSession();

    WebDriver createRemoteBrowserSession(String remoteUrl);
}