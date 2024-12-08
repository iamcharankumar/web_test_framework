package io.saucelabs.portal.qa.commons.web;

import io.saucelabs.portal.qa.exceptions.WebPageException;
import io.saucelabs.portal.qa.waits.WebBrowserWaiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebBrowserWaiter waiter;
    protected WebBrowserWaiter longWaiter;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebBrowserWaiter(this.driver);
        this.longWaiter = new WebBrowserWaiter(this.driver);
    }

    protected void validateAction(boolean condition, String errorMessage) {
        if (!condition)
            throw new WebPageException(errorMessage);
    }

    protected void validateNonEmptyText(String text, String errorMessage) {
        if (text.isEmpty())
            throw new WebPageException(errorMessage);
    }

    protected void clickElement(WebElement webElement, String errorMessage) {
        if (!webElement.isEnabled())
            throw new WebPageException(errorMessage);
        webElement.click();
    }
}