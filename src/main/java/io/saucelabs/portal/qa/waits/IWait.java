package io.saucelabs.portal.qa.waits;

import org.openqa.selenium.By;

import java.util.List;

public interface IWait<T> {

    void waitForVisibilityOf(final T element);

    T waitForPresenceOfElementLocated(final By locator);

    T waitForVisibilityOfElementLocated(final By locator);

    List<T> waitForVisibilityOfAllElementsLocatedBy(final By locator);

    List<T> waitForVisibilityOfAllElements(final T... elements);

    List<T> waitForVisibilityOfAllElements(final List<T> elements);

    List<T> waitForPresenceOfAllElementsLocatedBy(final By locator);

    Boolean waitForTextToBePresentInElement(final T element, final String text);

    Boolean waitForTextToBePresentInElementLocated(final By locator, final String text);

    Boolean waitForTextToBePresentInElementValue(final T element, final String text);

    Boolean waitForTextToBePresentInElementValue(final By locator, final String text);

    T waitForElementToBeClickable(final By locator);

    T waitForElementToBeClickable(final T element);
}