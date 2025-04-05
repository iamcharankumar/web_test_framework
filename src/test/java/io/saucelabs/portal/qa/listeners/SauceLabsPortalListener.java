package io.saucelabs.portal.qa.listeners;

import io.saucelabs.portal.qa.commons.BaseTest;
import io.saucelabs.portal.qa.commons.web.SauceLabsPortalConstants;
import io.saucelabs.portal.qa.utils.DiscordUtils;
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
import java.util.Date;

@Slf4j
public class SauceLabsPortalListener extends BaseTest implements ITestListener, ISuiteListener, IRetryAnalyzer {

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
        int passedTestCases = context.getPassedTests().size();
        int failedTestCases = context.getFailedTests().size();
        int skippedTestCases = context.getSkippedTests().size();
        int totalTestCases = passedTestCases + failedTestCases + skippedTestCases;
        String discordMessage = DiscordUtils.buildDiscordMessage(passedTestCases, failedTestCases, skippedTestCases, totalTestCases);
        DiscordUtils.sendMessageToChannel(discordMessage);
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
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver.get();
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile;
        try {
            if (testResult.isSuccess()) {
                destinationFile = new File(SauceLabsPortalConstants.DIRECTORY +
                        SauceLabsPortalConstants.PASS + File.separator
                        + SauceLabsPortalConstants.PASS_PREFIX
                        + testResult.getName() + "_" + new Date()
                        + SauceLabsPortalConstants.IMAGE_FORMAT);
                FileUtils.copyFile(sourceFile, destinationFile);
                log.info("Success scenario has been captured. PASSED Screenshot has been placed in the location {}",
                        destinationFile);
            } else {
                destinationFile = new File(SauceLabsPortalConstants.DIRECTORY
                        + SauceLabsPortalConstants.FAIL + File.separator
                        + SauceLabsPortalConstants.FAIL_PREFIX
                        + testResult.getName() + "_" + new Date()
                        + SauceLabsPortalConstants.IMAGE_FORMAT);
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
        return switch (status) {
            case 1 -> "SUCCESS";
            case 2 -> "FAILED";
            case 3 -> "SKIP";
            default -> null;
        };
    }
}