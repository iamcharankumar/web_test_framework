package io.saucelabs.portal.qa.listeners;

import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.exceptions.WebUtilsException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.zeroturnaround.zip.commons.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Slf4j
public class SauceLabsPortalListener implements ITestListener, ISuiteListener, IRetryAnalyzer {

    private Instant startDate;
    private int COUNT = 0;
    private static final int MAX_RETRY = 1;

    private static final ThreadLocal<WebDriver> WEB_DRIVER = new ThreadLocal<>();

    public static void setWebDriver(WebDriver driver) {
        WEB_DRIVER.set(driver);
    }

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
        log.info("Test Method {} is PASS.", concatenateTestMethodTestData(result, result.getParameters()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result);
        log.info("Test Method {} is FAIL.", concatenateTestMethodTestData(result, result.getParameters()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot(result);
        log.info("Test Method {} SKIP.", concatenateTestMethodTestData(result, result.getParameters()));
    }

    private void takeScreenshot(ITestResult testResult) {
        try {
            File sourceFile = ((TakesScreenshot) WEB_DRIVER.get()).getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();
            String statusPrefix = testResult.isSuccess() ? "PASS_" : "FAIL_";
            String filePath = getFilePath(testResult, statusPrefix, testName);
            FileUtils.copyFile(sourceFile, new File(filePath));
        } catch (IOException e) {
            log.error("Failed to capture screenshot for test: {}", testResult.getName(), e);
        }
    }

    private static String getFilePath(ITestResult testResult, String statusPrefix, String testName) {
        String directory = testResult.isSuccess() ? "/passed_screenshots/" : "/failed_screenshots/";
        String testData = (testResult.getParameters().length > 0) ? String.valueOf(testResult.getParameters()[0]) : "No_Params";
        return String.format("%s%s%s_%s_%s%s_%s_%s%s", "./src/test/resources/screenshots",
                directory, SauceLabsPortalConstants.BROWSER_NAME, SauceLabsPortalConstants.RUN_MODE,
                statusPrefix, testName, testData, new Date(), ".png").replaceAll(":", "\\:");
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

    private String concatenateTestMethodTestData(ITestResult result, Object[] testParameters) {
        StringBuilder testName = new StringBuilder();
        if (result != null) {
            if (result.getMethod().isDataDriven()) {
                testName.append(result.getName()).append("_");
                for (Object object : testParameters) {
                    testName.append(object.toString()).append("_");
                }
                return testName.substring(0, testName.length() - 1);
            } else
                return result.getName();
        } else
            throw new WebUtilsException("Test Method and Test Data Concatenation Failed!");
    }
}