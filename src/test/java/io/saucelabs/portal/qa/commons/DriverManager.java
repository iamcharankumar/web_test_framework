package io.saucelabs.portal.qa.commons;

import org.openqa.selenium.MutableCapabilities;

public interface DriverManager<T> {
	T getDriver();

	T getDriver(MutableCapabilities capabilities);

	void destroyDriver(T t);
}