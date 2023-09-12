package io.saucelabs.portal.qa.testcases;

import io.saucelabs.portal.qa.module.SauceLabsPortal;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class LoginPageTest extends SauceLabsPortalTestBase {

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        setUpSauceLabsPortal(method);
    }

    @Override
    public void setUpSauceLabsPortal(Method method) {
        log.info("Thread id in BeforeMethod for the test method :{} is {}.", method.getName(), Thread.currentThread().getId());
        SAUCELABS_PORTAL.set(new SauceLabsPortal(driver.get()));
        SAUCELABS_PORTAL.get().visit();
        driver.get().navigate().refresh();
        driver.get().manage().window().maximize();
    }

    @Test(description = "To verify, the login page title.")
    public void testLoginPageTitle() {
        String loginPageTitle = SAUCELABS_PORTAL.get().LOGIN_PAGE.getPageTitle();
        Assert.assertEquals(loginPageTitle, "Swag Labs", "Incorrect Login Page Title!");
        log.info("Verified the Login Page title: " + loginPageTitle);
    }

    @Test(description = "To verify, the login page header text.")
    public void testLoginPageHeaderText() {
        String loginPageHeaderText = SAUCELABS_PORTAL.get().LOGIN_PAGE.getHeaderText();
        Assert.assertEquals(loginPageHeaderText, "Swag Labs", "Incorrect Login Page Header Text!");
        log.info("Verified the Login Page Header Text {} ", loginPageHeaderText);
    }

    @Test(description = "To verify, the landing page as Home Page from Login Page.")
    public void testSuccessfulLogin() {
        boolean isLogin = SAUCELABS_PORTAL.get().LOGIN_PAGE.isLoginSuccess("standard_user", "secret_sauce");
        Assert.assertTrue(isLogin);
        log.info("Verified the login success status {}", isLogin);
    }
}