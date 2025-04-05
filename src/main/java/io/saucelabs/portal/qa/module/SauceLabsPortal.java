package io.saucelabs.portal.qa.module;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.pages.CartPage;
import io.saucelabs.portal.qa.pages.HomePage;
import io.saucelabs.portal.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SauceLabsPortal {
    private final WebDriver SAUCELABS_WEBDRIVER;
    public final LoginPage LOGIN_PAGE;
    public final HomePage HOME_PAGE;
    public final CartPage CART_PAGE;

    public SauceLabsPortal(WebDriver driver) {
        this.SAUCELABS_WEBDRIVER = driver;
        LOGIN_PAGE = PageFactory.initElements(this.SAUCELABS_WEBDRIVER, LoginPage.class);
        HOME_PAGE = PageFactory.initElements(this.SAUCELABS_WEBDRIVER, HomePage.class);
        CART_PAGE = PageFactory.initElements(this.SAUCELABS_WEBDRIVER, CartPage.class);
    }

    public void visit() {
        SAUCELABS_WEBDRIVER.navigate().to(SauceLabsPortalConstants.CONFIG_LOADER.getSauceLabsPortalUrl());
    }
}