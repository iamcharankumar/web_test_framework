package io.saucelabs.portal.qa.drivermanager;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.commons.web.WebPortalConstants;
import io.saucelabs.portal.qa.exceptions.UnSupportedBrowserException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

@AllArgsConstructor
public class WebDriverFactory {

    protected String browserName;

    public WebDriver createLocalBrowserSession() {
        return switch (browserName.toLowerCase()) {
            case SauceLabsPortalConstants.CHROME_BROWSER -> new ChromeDriver();
            case SauceLabsPortalConstants.FIREFOX_BROWSER -> new FirefoxDriver();
            default -> throw new UnSupportedBrowserException("Accepted Local browsers are Chrome or Firefox.");
        };
    }

    public WebDriver createHeadlessBrowserSession() {
        switch (browserName.toLowerCase()) {
            case SauceLabsPortalConstants.CHROME_BROWSER:
                ChromeOptions chromeOptions = getChromeOptions();
                return new ChromeDriver(chromeOptions);
            case SauceLabsPortalConstants.FIREFOX_BROWSER:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new UnSupportedBrowserException("Accepted Headless browsers are Chrome or Firefox.");
        }
    }

    @SneakyThrows
    public WebDriver createRemoteBrowserSession(String url) {
        return switch (browserName.toLowerCase()) {
            case SauceLabsPortalConstants.CHROME_BROWSER -> {
                ChromeOptions options = getChromeOptions();
                yield new RemoteWebDriver(new URL(url), options);
            }
            case SauceLabsPortalConstants.FIREFOX_BROWSER -> new RemoteWebDriver(new URL(url), new FirefoxOptions());
            default -> throw new UnSupportedBrowserException("Accepted Remote browsers are Chrome or Firefox.");
        };
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(WebPortalConstants.BROWSER_OPTIONS_HEADLESS_ARGUMENT);
        chromeOptions.addArguments(WebPortalConstants.BROWSER_OPTIONS_WINDOW_SIZE_ARGUMENT);
        chromeOptions.addArguments(WebPortalConstants.BROWSER_OPTIONS_START_MAXIMIZE_ARGUMENT);
        chromeOptions.addArguments(WebPortalConstants.BROWSER_OPTIONS_IGNORE_CERTS_ARGUMENT);
        chromeOptions.addArguments(WebPortalConstants.BROWSER_OPTIONS_DISABLE_EXTENSIONS_ARGUMENT);
        chromeOptions.addArguments(WebPortalConstants.BROWSER_OPTIONS_NO_SANDBOX_ARGUMENT);
        chromeOptions.addArguments(WebPortalConstants.BROWSER_OPTIONS_DISABLE_DEV_SHM_USAGE_ARGUMENT);
        chromeOptions.addArguments(WebPortalConstants.BROWSER_OPTIONS_REMOTE_ALLOW_ORIGINS_ARGUMENT);
        chromeOptions.setImplicitWaitTimeout(WebPortalConstants.BROWSER_OPTIONS_IMPLICIT_WAIT_TIMEOUT);
        return chromeOptions;
    }
}