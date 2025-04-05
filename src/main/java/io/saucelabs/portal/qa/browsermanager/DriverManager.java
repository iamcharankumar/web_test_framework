package io.saucelabs.portal.qa.browsermanager;

public interface DriverManager<T> {

    T getDriver();

    void destroyDriver(T t);
}