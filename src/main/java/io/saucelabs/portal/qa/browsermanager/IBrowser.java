package io.saucelabs.portal.qa.browsermanager;

import org.openqa.selenium.WebDriver;

public interface IBrowser {

    WebDriver createLocalBrowserSession();

    WebDriver createHeadlessBrowserSession();

    WebDriver createRemoteBrowserSession(String remoteUrl);
}