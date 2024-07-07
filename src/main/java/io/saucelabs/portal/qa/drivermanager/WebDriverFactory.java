package io.saucelabs.portal.qa.drivermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.exceptions.UnSupportedBrowserException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;

@AllArgsConstructor
public class WebDriverFactory {

    protected String browserName;

    public WebDriver createLocalBrowserSession() {
        return switch (browserName.toLowerCase()) {
            case SauceLabsPortalConstants.CHROME_BROWSER -> new ChromeDriver();
            case SauceLabsPortalConstants.FIREFOX_BROWSER -> new FirefoxDriver();
            default -> throw new UnSupportedBrowserException("Accepted browsers are Chrome or Firefox.");
        };
    }

    @SneakyThrows
    public WebDriver createRemoteBrowserSession(String url) {
        switch (browserName.toLowerCase()) {
            case SauceLabsPortalConstants.CHROME_BROWSER:
                ChromeOptions options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
                options.setImplicitWaitTimeout(Duration.ofSeconds(5));
                options.addArguments(SauceLabsPortalConstants.MAXIMIZE);
                return new RemoteWebDriver(new URL(url), options);
            case SauceLabsPortalConstants.FIREFOX_BROWSER:
                return new RemoteWebDriver(new URL(url), new FirefoxOptions());
            default:
                throw new UnSupportedBrowserException("Accepted browsers are Chrome or Firefox.");
        }
    }
}