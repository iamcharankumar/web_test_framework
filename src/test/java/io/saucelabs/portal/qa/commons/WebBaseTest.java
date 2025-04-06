package io.saucelabs.portal.qa.commons;

import io.saucelabs.portal.qa.browsermanager.IDriverManager;
import io.saucelabs.portal.qa.browsermanager.WebDriverManager;
import io.saucelabs.portal.qa.listeners.SauceLabsPortalListener;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

@Slf4j
public abstract class WebBaseTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static IDriverManager<WebDriver> driverManager;
    protected SoftAssert softAssert = new SoftAssert();

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        driverManager = new WebDriverManager();
        log.info("WebDriver manager has been initiated");
    }

    @BeforeMethod(alwaysRun = true)
    public void init() {
        driver.set(driverManager.getDriver());
        log.info("WebDriver has been set.");
        SauceLabsPortalListener.setWebDriver(driver.get());
    }

    @AfterMethod(alwaysRun = true)
    public void destroy() {
        driverManager.destroyDriver(driver.get());
        driver.remove();
        log.info("WebDriver manager has been removed/destroyed");
    }
}