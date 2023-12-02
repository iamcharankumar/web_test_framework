package io.saucelabs.portal.qa.commons;

import io.saucelabs.portal.qa.drivermanager.DriverManager;
import io.saucelabs.portal.qa.drivermanager.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

@Slf4j
public abstract class BaseTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static DriverManager<WebDriver> driverManager;

    @BeforeSuite(alwaysRun = true)
    public void setup(ITestContext context) {
        driverManager = new WebDriverManager();
        log.info("WebDriver manager has been initiated");
    }

    @BeforeMethod(alwaysRun = true)
    public void init(Method method) {
        driver.set(driverManager.getDriver());
        log.info("WebDriver has been set");
    }

    @AfterMethod(alwaysRun = true)
    public void destroy(ITestResult result) {
        driverManager.destroyDriver(driver.get());
        driver.remove();
        log.info("WebDriver manager has been removed/destroyed");
    }
}