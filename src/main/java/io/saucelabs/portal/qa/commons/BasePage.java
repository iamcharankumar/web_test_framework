package io.saucelabs.portal.qa.commons;

import io.saucelabs.portal.qa.waits.WebBrowserWaiter;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebBrowserWaiter waiter;
    protected WebBrowserWaiter longWaiter;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebBrowserWaiter(this.driver);
        this.longWaiter = new WebBrowserWaiter(this.driver);
    }
}