package io.saucelabs.portal.qa.module;

import io.saucelabs.portal.qa.commons.SauceLabsPortalConstant;
import io.saucelabs.portal.qa.pages.CartPage;
import io.saucelabs.portal.qa.pages.HomePage;
import io.saucelabs.portal.qa.pages.LoginPage;
import io.saucelabs.portal.qa.utils.PropertiesHelper;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SauceLabsPortal {

    private static final String SAUCELABS_PROPERTIES = SauceLabsPortalConstant.SAUCELABS_PROTAL_PROPERTIES_FILE;
    private final PropertiesHelper PROPERTIES_HELPER;
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
        PROPERTIES_HELPER = new PropertiesHelper();
        this.SAUCELABS_URL = PROPERTIES_HELPER.getProperty(SAUCELABS_PROPERTIES, SauceLabsPortalConstant.SAUCELABS_URL);
        this.SAUCELABS_USERNAME = PROPERTIES_HELPER.getProperty(SAUCELABS_PROPERTIES, SauceLabsPortalConstant.USER_NAME);
        this.SAUCELABS_PASSWORD = PROPERTIES_HELPER.getProperty(SAUCELABS_PROPERTIES, SauceLabsPortalConstant.PASSWORD);
        LOGIN_PAGE = PageFactory.initElements(this.SAUCELABS_WEBDRIVER, LoginPage.class);
        HOME_PAGE = PageFactory.initElements(this.SAUCELABS_WEBDRIVER, HomePage.class);
        CART_PAGE = PageFactory.initElements(this.SAUCELABS_WEBDRIVER, CartPage.class);
    }

    public void visit() {
        SAUCELABS_WEBDRIVER.navigate().to(SAUCELABS_URL);
    }

}