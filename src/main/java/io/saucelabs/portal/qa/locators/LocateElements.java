package io.saucelabs.portal.qa.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LocateElements implements ILocateElements<WebElement> {

    private final WebDriver WEB_DRIVER;

    public LocateElements(WebDriver webDriver) {
        this.WEB_DRIVER = webDriver;
    }

    @Override
    public WebElement getById(String id) {
        return WEB_DRIVER.findElement(By.id(id));
    }

    @Override
    public WebElement getByClassName(String className) {
        return WEB_DRIVER.findElement(By.className(className));
    }

    @Override
    public List<WebElement> getByClassNameList(String className) {
        return WEB_DRIVER.findElements(By.className(className));
    }

    @Override
    public WebElement getByXpath(String xpath) {
        return WEB_DRIVER.findElement(By.xpath(xpath));
    }

    @Override
    public WebElement getByCssSelector(String cssSelector) {
        return WEB_DRIVER.findElement(By.cssSelector(cssSelector));
    }

    @Override
    public WebElement getByTagName(String tagName) {
        return WEB_DRIVER.findElement(By.tagName(tagName));
    }

    @Override
    public WebElement getByName(String name) {
        return WEB_DRIVER.findElement(By.name(name));
    }

    @Override
    public WebElement getByLinkText(String linkText) {
        return WEB_DRIVER.findElement(By.linkText(linkText));
    }

    @Override
    public WebElement getByPartialLinkText(String partialLinkText) {
        return WEB_DRIVER.findElement(By.partialLinkText(partialLinkText));
    }
}