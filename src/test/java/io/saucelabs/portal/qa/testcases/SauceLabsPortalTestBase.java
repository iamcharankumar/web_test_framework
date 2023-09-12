package io.saucelabs.portal.qa.testcases;

import io.saucelabs.portal.qa.module.SauceLabsPortal;
import io.saucelabs.portal.qa.commons.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

@Slf4j
public class SauceLabsPortalTestBase extends BaseTest implements ITest {

    protected static final ThreadLocal<SauceLabsPortal> SAUCELABS_PORTAL = new ThreadLocal<SauceLabsPortal>();
    protected static final ThreadLocal<String> SAUCELABS_BASE_TEST = new ThreadLocal<String>();

    @BeforeMethod(alwaysRun = true)
    public void setUpSauceLabsPortal(Method method) {
        log.info("Thread id in BeforeMethod for the test method : {} is {}.", method.getName(), Thread.currentThread().getId());
        SAUCELABS_PORTAL.set(new SauceLabsPortal(driver.get()));
        SAUCELABS_PORTAL.get().visit();
        driver.get().navigate().refresh();
        driver.get().manage().window().maximize();
        SAUCELABS_PORTAL.get().LOGIN_PAGE.isLoginSuccess(SAUCELABS_PORTAL.get().getSAUCELABS_USERNAME(), SAUCELABS_PORTAL.get().getSAUCELABS_PASSWORD());

    }

    @Override
    public String getTestName() {
        return SAUCELABS_BASE_TEST.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownSauceLabsPortal(Method method) {
        log.info("Thread id in AfterMethod for the test method :{} is {}.", method.getName(), Thread.currentThread().getId());
        SAUCELABS_PORTAL.remove();
        SAUCELABS_BASE_TEST.remove();
    }
}