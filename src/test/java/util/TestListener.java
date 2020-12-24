package util;

import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    private final Logger log = LogManager.getRootLogger();

    public void onTestStart(ITestResult iTestResult) {
        log.info("Test start execution " + iTestResult.getName());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.info(iTestResult.getName() + " successfully completed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.error(iTestResult.getName() + " failed");
        saveScreenshot();
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.warn(iTestResult.getName() + " skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.warn(iTestResult.getName() + " particularly completed");
    }

    public void onStart(ITestContext iTestContext) {
        log.info("Testing started");
    }

    public void onFinish(ITestContext iTestContext) {
        log.info("Testing completed");
    }

    private void saveScreenshot(){
        File screenCapture = ((TakesScreenshot)DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                            + getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            log.error("Cannot create screenshot");
        }
    }

    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }
}