package io.saucelabs.portal.qa.locators;

import java.util.List;

public interface ILocateElements<T> {

    T getById(String id);

    T getByClassName(String className);

    List<T> getByClassNameList(String className);

    T getByXpath(String xpath);

    T getByCssSelector(String cssSelector);

    T getByTagName(String tagName);

    T getByName(String name);

    T getByLinkText(String linkText);

    T getByPartialLinkText(String partialLinkText);
}