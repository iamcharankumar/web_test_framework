package io.saucelabs.portal.qa.devtools;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.network.Network;

import java.util.Optional;

@Slf4j
public class ChromeDevTools implements ICdp {

    private final DevTools DEVTOOLS;

    public ChromeDevTools(WebDriver driver) {
        DEVTOOLS = ((ChromeDriver) driver).getDevTools();
    }

    @Override
    public void enableDevTools() {
        DEVTOOLS.createSession();
    }

    @Override
    public void enableNetwork() {
        DEVTOOLS.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
    }

    @Override
    public void monitorRequest() {
        DEVTOOLS.addListener(Network.requestWillBeSent(), requestWillBeSent -> {
            log.info("Request URL: {}", requestWillBeSent.getRequest().getUrl());
            log.info("Request Method; {}", requestWillBeSent.getRequest().getMethod());
            log.info("Request Headers: {}", requestWillBeSent.getRequest().getHeaders().toJson());
        });
    }
}