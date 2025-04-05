package io.saucelabs.portal.qa.drivermanager;

public interface DriverManager<T> {

    T getDriver();

    void destroyDriver(T t);
}