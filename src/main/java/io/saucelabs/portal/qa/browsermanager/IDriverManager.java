package io.saucelabs.portal.qa.browsermanager;

public interface IDriverManager<T> {

    T getDriver();

    void destroyDriver(T t);
}