package io.saucelabs.portal.qa.listeners;

import io.saucelabs.portal.qa.commons.SauceLabsPortalConstant;
import io.saucelabs.portal.qa.commons.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.zeroturnaround.zip.commons.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Slf4j
public class SauceLabsPortalListener extends BaseTest implements ITestListener, ISuiteListener, IRetryAnalyzer {

    private Instant startDate;
    private int COUNT = 0;
    private static final int MAX_RETRY = 3;

    @Override
    public void onStart(ISuite suite) {
        startDate = Instant.now();
        log.info("Sauce Labs Test Suite started executing at {}.", startDate);
    }

    @Override
    public void onFinish(ISuite suite) {
        Instant endDate = Instant.now();
        Duration timeElapsed = Duration.between(startDate, endDate);
        log.info("Sauce Labs Test Suite finished executing in {} seconds.", timeElapsed.getSeconds());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot(result);
    }

    private void takeScreenshot(ITestResult testResult) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver.get();
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile;
        try {
            if (testResult.isSuccess()) {
                destinationFile = new File(SauceLabsPortalConstant.DIRECTORY +
                        SauceLabsPortalConstant.PASS + File.separator
                        + SauceLabsPortalConstant.PASS_PREFIX
                        + testResult.getName() + "_" + new Date()
                        + SauceLabsPortalConstant.IMAGE_FORMAT);
                FileUtils.copyFile(sourceFile, destinationFile);
                log.info("Success scenario has been captured. PASSED Screenshot has been placed in the location {}",
                        destinationFile);
            } else {
                destinationFile = new File(SauceLabsPortalConstant.DIRECTORY
                        + SauceLabsPortalConstant.FAIL + File.separator
                        + SauceLabsPortalConstant.FAIL_PREFIX
                        + testResult.getName() + "_" + new Date()
                        + SauceLabsPortalConstant.IMAGE_FORMAT);
                FileUtils.copyFile(sourceFile, destinationFile);
                log.info("Failed scenario has been captured. FAILED Screenshot has been placed in the location {}",
                        destinationFile);
            }
        } catch (IOException e) {
            log.error("Unable to capture the screenshots for Sauce Labs Portal {}", e.getMessage());
        }
    }

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (COUNT < MAX_RETRY) {
                log.info("Retrying test for {} time(s) for the test method {} with test status {}.",
                        COUNT + 1, result.getName(), getTestStatusName(result.getStatus()));
                COUNT++;
                return true;
            }
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