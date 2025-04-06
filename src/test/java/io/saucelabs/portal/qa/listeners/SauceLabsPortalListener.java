package io.saucelabs.portal.qa.listeners;

import io.saucelabs.portal.qa.commons.WebBaseTest;
import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.zeroturnaround.zip.commons.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Slf4j
public class SauceLabsPortalListener extends WebBaseTest implements ITestListener, ISuiteListener, IRetryAnalyzer {

    private Instant startDate;
    private int COUNT = 0;
    private static final int MAX_RETRY = 1;

    @Override
    public void onStart(ISuite suite) {
        startDate = Instant.now();
        log.info("Web Test Suite {} started executing at {}.", suite.getName(), startDate);
    }

    @Override
    public void onFinish(ITestContext context) {
        Instant endDate = Instant.now();
        Duration timeElapsed = Duration.between(startDate, endDate);
        log.info("Sauce Labs Test Suite finished executing in {} seconds.", timeElapsed.getSeconds());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result);
        log.info("Test Method {} is PASS.", TestUtils.concatenateTestMethodTestData(result, result.getParameters()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result);
        log.info("Test Method {} is FAIL.", TestUtils.concatenateTestMethodTestData(result, result.getParameters()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot(result);
        log.info("Test Method {} SKIP.", TestUtils.concatenateTestMethodTestData(result, result.getParameters()));
    }

    private void takeScreenshot(ITestResult testResult) {
        try {
            File sourceFile = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();
            String statusPrefix = testResult.isSuccess() ? "PASS_" : "FAIL_";
            String directory = testResult.isSuccess() ? "/passed_screenshots/" : "/failed_screenshots/";
            String testData = (testResult.getParameters().length > 0) ? String.valueOf(testResult.getParameters()[0]) : "No_Params";
            String filePath = String.format("%s%s%s_%s_%s%s_%s_%s%s", "./src/test/resources/screenshots",
                    directory, SauceLabsPortalConstants.BROWSER_NAME, SauceLabsPortalConstants.RUN_MODE,
                    statusPrefix, testName, testData, Instant.now().toString().replace(":", "_"), ".png");
            FileUtils.copyFile(sourceFile, new File(filePath));
        } catch (IOException e) {
            log.error("Failed to capture screenshot for test: {}", testResult.getName(), e);
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
        return switch (status) {
            case 1 -> "SUCCESS";
            case 2 -> "FAILED";
            case 3 -> "SKIP";
            default -> null;
        };
    }
}