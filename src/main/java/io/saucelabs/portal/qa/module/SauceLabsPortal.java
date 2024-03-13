package io.saucelabs.portal.qa.module;

import io.saucelabs.portal.qa.pages.CartPage;
import io.saucelabs.portal.qa.pages.HomePage;
import io.saucelabs.portal.qa.pages.LoginPage;
import io.saucelabs.portal.qa.utils.ConfigLoader;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SauceLabsPortal {
    private final WebDriver SAUCELABS_WEBDRIVER;
    private final String SAUCELABS_URL;
    @Getter
    public final String SAUCELABS_USERNAME;
    @Getter
    public final String SAUCELABS_PASSWORD;

    public final LoginPage LOGIN_PAGE;
    public final HomePage HOME_PAGE;
    public final CartPage CART_PAGE;

    public SauceLabsPortal(WebDriver driver) {
        this.SAUCELABS_WEBDRIVER = driver;
        this.SAUCELABS_URL = ConfigLoader.getInstance().getSauceLabsPortalUrl();
        this.SAUCELABS_USERNAME = ConfigLoader.getInstance().getSauceLabsPortalUserName();
        this.SAUCELABS_PASSWORD = ConfigLoader.getInstance().getSauceLabsPortalPassword();
        LOGIN_PAGE = PageFactory.initElements(this.SAUCELABS_WEBDRIVER, LoginPage.class);
        HOME_PAGE = PageFactory.initElements(this.SAUCELABS_WEBDRIVER, HomePage.class);
        CART_PAGE = PageFactory.initElements(this.SAUCELABS_WEBDRIVER, CartPage.class);
    }

    public void visit() {
        SAUCELABS_WEBDRIVER.navigate().to(SAUCELABS_URL);
    }
}