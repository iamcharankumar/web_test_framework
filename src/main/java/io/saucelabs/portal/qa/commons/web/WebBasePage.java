package io.saucelabs.portal.qa.commons.web;

import io.saucelabs.portal.qa.exceptions.WebUtilsException;
import io.saucelabs.portal.qa.waits.WebBrowserWaiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebBasePage {

    protected WebDriver driver;
    protected WebBrowserWaiter waiter;
    protected WebBrowserWaiter longWaiter;

    protected WebBasePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebBrowserWaiter(this.driver);
        this.longWaiter = new WebBrowserWaiter(this.driver);
    }

    protected boolean isWebElementDisplayed(WebElement webElement) {
        return webElement.isDisplayed();
    }

    protected boolean isWebElementEnabled(WebElement webElement) {
        return webElement.isEnabled();
    }

    protected boolean isWebElementSelected(WebElement webElement) {
        return webElement.isSelected();
    }

    protected String getWebElementText(WebElement webElement) {
        if (!isWebElementDisplayed(webElement))
            throw new WebUtilsException("WebElement %s is not displayed!".formatted(webElement));
        return webElement.getText();
    }

    protected void clickWebElement(WebElement webElement) {
        if (!isWebElementDisplayedAndEnabled(webElement))
            throw new WebUtilsException("WebElement %s is not displayed and enabled!".formatted(webElement));
        webElement.click();
    }

    protected void fillText(WebElement webElement, String text) {
        if (!isWebElementDisplayedAndEnabled(webElement))
            throw new WebUtilsException("WebElement %s is not displayed and enabled!".formatted(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    private boolean isWebElementDisplayedAndEnabled(WebElement webElement) {
        return isWebElementDisplayed(webElement) && isWebElementEnabled(webElement);
    }
}