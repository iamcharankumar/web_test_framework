package io.saucelabs.portal.qa.module;

import io.saucelabs.portal.qa.commons.WebConstants;
import io.saucelabs.portal.qa.pages.CartPage;
import io.saucelabs.portal.qa.pages.HomePage;
import io.saucelabs.portal.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class SauceLabsPortal {

    private final WebDriver SAUCE_LABS_WEBDRIVER;
    public final LoginPage LOGIN_PAGE;
    public final HomePage HOME_PAGE;
    public final CartPage CART_PAGE;

    public SauceLabsPortal(WebDriver driver) {
        this.SAUCE_LABS_WEBDRIVER = driver;
        LOGIN_PAGE = new LoginPage(SAUCE_LABS_WEBDRIVER);
        HOME_PAGE = new HomePage(SAUCE_LABS_WEBDRIVER);
        CART_PAGE = new CartPage(SAUCE_LABS_WEBDRIVER);
    }

    public void visit() {
        SAUCE_LABS_WEBDRIVER.navigate().to(WebConstants.CONFIG_LOADER.getSauceLabsPortalUrl());
    }
}