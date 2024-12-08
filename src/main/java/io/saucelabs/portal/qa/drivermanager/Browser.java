package io.saucelabs.portal.qa.drivermanager;

import org.openqa.selenium.WebDriver;

public interface Browser {

    WebDriver createWebDriver();

    WebDriver createRemoteWebDriver(String url);
}