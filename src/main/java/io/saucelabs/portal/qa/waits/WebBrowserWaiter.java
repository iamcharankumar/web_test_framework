package io.saucelabs.portal.qa.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebBrowserWaiter implements IWait<WebElement> {

    private final WebDriverWait WAIT;
    private static final int TIMEOUT = 5;

    public WebBrowserWaiter(WebDriver driver) {
        WAIT = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public WebBrowserWaiter(WebDriver driver, int timeoutInSeconds) {
        WAIT = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    @Override
    public WebElement waitForPresenceOfElementLocated(By locator) {
        return WAIT.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public WebElement waitForVisibilityOfElementLocated(By locator) {
        return WAIT.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public List<WebElement> waitForVisibilityOfAllElementsLocatedBy(By locator) {
        return WAIT.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    @Override
    public List<WebElement> waitForVisibilityOfAllElements(WebElement... elements) {
        return WAIT.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    @Override
    public List<WebElement> waitForVisibilityOfAllElements(List<WebElement> elements) {
        return WAIT.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    @Override
    public void waitForVisibilityOf(WebElement element) {
        WAIT.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public List<WebElement> waitForPresenceOfAllElementsLocatedBy(By locator) {
        return WAIT.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    @Override
    public Boolean waitForTextToBePresentInElement(WebElement element, String text) {
        return WAIT.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    @Override
    public Boolean waitForTextToBePresentInElementLocated(By locator, String text) {
        return WAIT.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    @Override
    public Boolean waitForTextToBePresentInElementValue(WebElement element, String text) {
        return WAIT.until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    @Override
    public Boolean waitForTextToBePresentInElementValue(By locator, String text) {
        return WAIT.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    @Override
    public WebElement waitForElementToBeClickable(By locator) {
        return WAIT.until(ExpectedConditions.elementToBeClickable(locator));
    }

    @Override
    public WebElement waitForElementToBeClickable(WebElement element) {
        return WAIT.until(ExpectedConditions.elementToBeClickable(element));
    }
}
