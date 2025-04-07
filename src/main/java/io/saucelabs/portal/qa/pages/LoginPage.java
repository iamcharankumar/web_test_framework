package io.saucelabs.portal.qa.pages;

import org.openqa.selenium.WebDriver;

public class LoginPage extends SauceLabsBasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getHeaderText() {
        return getWebElementText(locateElements.getByClassName("login_logo"));
    }

    public boolean isLoginSuccess(String loginUserName, String loginPassword) {
        fillText(locateElements.getById("user-name"), loginUserName);
        fillText(locateElements.getById("password"), loginPassword);
        clickWebElement(locateElements.getById("login-button"));
        return true;
    }
}