package io.saucelabs.portal.qa.commons.web;

import io.saucelabs.portal.qa.exceptions.WebUtilsException;
import io.saucelabs.portal.qa.waits.WebBrowserWaiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

public abstract class WebBasePage {

    protected WebDriver driver;
    protected WebBrowserWaiter waiter;
    protected WebBrowserWaiter longWaiter;
    private static final String WEB_ELEMENT_ERROR_MESSAGE = "WebElement cannot be null";

    protected WebBasePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebBrowserWaiter(this.driver);
        this.longWaiter = new WebBrowserWaiter(this.driver);
    }

    protected boolean isWebElementDisplayed(WebElement webElement) {
        Objects.requireNonNull(webElement, WEB_ELEMENT_ERROR_MESSAGE);
        return webElement.isDisplayed();
    }

    protected boolean isWebElementEnabled(WebElement webElement) {
        Objects.requireNonNull(webElement, WEB_ELEMENT_ERROR_MESSAGE);
        return webElement.isEnabled();
    }

    protected boolean isWebElementSelected(WebElement webElement) {
        Objects.requireNonNull(webElement, WEB_ELEMENT_ERROR_MESSAGE);
        return webElement.isSelected();
    }

    protected String getWebElementText(WebElement webElement) {
        Objects.requireNonNull(webElement, WEB_ELEMENT_ERROR_MESSAGE);
        if (!isWebElementDisplayed(webElement))
            throw new WebUtilsException("WebElement %s is not displayed!".formatted(webElement));
        return webElement.getText();
    }

    protected void clickWebElement(WebElement webElement) {
        Objects.requireNonNull(webElement, WEB_ELEMENT_ERROR_MESSAGE);
        if (!isWebElementDisplayedAndEnabled(webElement))
            throw new WebUtilsException("WebElement %s is not displayed and enabled!".formatted(webElement));
        webElement.click();
    }

    protected void fillText(WebElement webElement, String text) {
        Objects.requireNonNull(webElement, WEB_ELEMENT_ERROR_MESSAGE);
        Objects.requireNonNull(text, "Text cannot be null");
        if (!isWebElementDisplayedAndEnabled(webElement))
            throw new WebUtilsException("WebElement %s is not displayed and enabled!".formatted(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected void selectWebElementByVisibleText(WebElement webElement, String visibleText) {
        Objects.requireNonNull(webElement, WEB_ELEMENT_ERROR_MESSAGE);
        Objects.requireNonNull(visibleText, "Visible text cannot be null");
        new Select(webElement).selectByVisibleText(visibleText);
    }

    private boolean isWebElementDisplayedAndEnabled(WebElement webElement) {
        Objects.requireNonNull(webElement, WEB_ELEMENT_ERROR_MESSAGE);
        return isWebElementDisplayed(webElement) && isWebElementEnabled(webElement);
    }
}