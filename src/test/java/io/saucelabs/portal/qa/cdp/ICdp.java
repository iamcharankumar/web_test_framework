package io.saucelabs.portal.qa.cdp;

import org.openqa.selenium.chromium.ChromiumDriver;

import java.util.List;

public interface ICdp<T> {
    T getDevTools(ChromiumDriver chromiumDriver);

    void captureNetworkCalls();

    void blockUrls(List<String> blockingUrls);
}