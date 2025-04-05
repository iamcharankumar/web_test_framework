package io.saucelabs.portal.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends SauceLabsBasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getHeaderText() {
        return getWebElementText(loginLogo);
    }

    public boolean isLoginSuccess(String loginUserName, String loginPassword) {
        fillText(userName, loginUserName);
        fillText(password, loginPassword);
        clickWebElement(loginButton);
        return true;
    }
}