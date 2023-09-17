package io.saucelabs.portal.qa.cdp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v115.network.Network;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class CdpTools implements ICdp<DevTools> {

    private DevTools devTools;

    @Override
    public DevTools getDevTools(ChromiumDriver chromiumDriver) {
        devTools = chromiumDriver.getDevTools();
        return devTools;
    }

    @Override
    public void captureNetworkCalls() {
        devTools.send(Network.enable(Optional.empty(),
                Optional.empty(), Optional.empty()));
        log.info("Capture Network Calls Web Browser Network Tab enabled.");
        devTools.addListener(Network.requestWillBeSent(), request -> {
            log.info("Request URL : {} Request Method : {} ", request.getRequest().getUrl(), request.getRequest().getMethod());
            log.info("Request Headers: {} ", request.getRequest().getHeaders().toJson());
        });
        devTools.addListener(Network.responseReceived(), response -> {
            log.info("Response URL: {} Response Status: {} ", response.getResponse().getUrl(), response.getResponse().getStatus());
            log.info("Response Headers: {} ", response.getResponse().getHeaders().toJson());
            log.info("Response MIME Types: {}", response.getResponse().getMimeType());
        });
    }

    @Override
    public void blockUrls(List<String> blockingUrls) {
        devTools.send(Network.enable(Optional.of(1000000), Optional.of(1000000), Optional.of(1000000)));
        log.info("Block Urls Web Browser Network Tab enabled.");
        devTools.send(Network.setBlockedURLs(Arrays.asList("*.jpg", "*.svg", "*.png")));
        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            log.info("Blocking reason {} ", loadingFailed.getBlockedReason().orElse(null));
        });
    }
}