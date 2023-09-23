package io.saucelabs.portal.qa.listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class SauceLabsPortalRetryAnalyzer implements IRetryAnalyzer {
    private int COUNT = 0;

    @Override
    public boolean retry(ITestResult result) {
        int MAX_RETRY = 3;
        if (!result.isSuccess() && COUNT < MAX_RETRY) {
            log.info("Retrying test for {} time(s) for the test method {} with test status {}.",
                    COUNT + 1, result.getName(), getTestStatusName(result.getStatus()));
            COUNT++;
            return true;
        }
        log.info("Retrying for the test method {} is exhausted.", result.getName());
        return false;
    }

    private String getTestStatusName(int status) {
        String testStatus = null;
        switch (status) {
            case 1:
                testStatus = "SUCCESS";
                break;
            case 2:
                testStatus = "FAILED";
                break;
            case 3:
                testStatus = "SKIP";
                break;
        }
        return testStatus;
    }
}