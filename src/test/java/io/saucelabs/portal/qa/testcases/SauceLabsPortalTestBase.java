package io.saucelabs.portal.qa.testcases;

import io.saucelabs.portal.qa.commons.WebBaseTest;
import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.module.SauceLabsPortal;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

@Slf4j
public class SauceLabsPortalTestBase extends WebBaseTest {

    protected static final ThreadLocal<SauceLabsPortal> SAUCELABS_PORTAL = new ThreadLocal<SauceLabsPortal>();

    @BeforeMethod(alwaysRun = true)
    public void setUpSauceLabsPortal(Method method) {
        log.info("Thread id in BeforeMethod for the test method : {} is {}.", method.getName(), Thread.currentThread().getId());
        SAUCELABS_PORTAL.set(new SauceLabsPortal(driver.get()));
        SAUCELABS_PORTAL.get().visit();
        driver.get().navigate().refresh();
        driver.get().manage().window().maximize();
        SAUCELABS_PORTAL.get().LOGIN_PAGE.isLoginSuccess(SauceLabsPortalConstants.CONFIG_LOADER.getSauceLabsPortalUserName(),
                SauceLabsPortalConstants.CONFIG_LOADER.getSauceLabsPortalPassword());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownSauceLabsPortal(Method method) {
        log.info("Thread id in AfterMethod for the test method :{} is {}.", method.getName(), Thread.currentThread().getId());
        SAUCELABS_PORTAL.remove();
    }
}