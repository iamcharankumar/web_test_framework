package io.saucelabs.portal.qa.drivermanager;

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
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new UnSupportedBrowserException("Accepted browsers are Chrome or Firefox.");
        }
        return driver;
    }

    @SneakyThrows
    public WebDriver createRemoteBrowserSession(String url) {
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
                options.setImplicitWaitTimeout(Duration.ofSeconds(5));
                options.addArguments("--start-maximized");
                driver = new RemoteWebDriver(new URL(url), options);
                break;
            case "firefox":
                driver = new RemoteWebDriver(new URL(url), new FirefoxOptions());
                break;
            default:
                throw new UnSupportedBrowserException("Accepted browsers are Chrome or Firefox.");
        }
        return driver;
    }
}