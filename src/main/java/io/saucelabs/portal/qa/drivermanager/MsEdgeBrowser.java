package io.saucelabs.portal.qa.drivermanager;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class MsEdgeBrowser implements Browser {

    @Override
    public WebDriver createWebDriver() {
        return new EdgeDriver();
    }

    @Override
    public WebDriver createRemoteWebDriver(String url) {
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), new EdgeOptions());
        } catch (MalformedURLException e) {
            log.error("Malformed Remote URL! {}", e.getLocalizedMessage());
        }
        return driver;
    }
}
