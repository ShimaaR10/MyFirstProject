package com.shimaa.frameworkpractice.listeners;

import com.shimaa.frameworkpractice.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Get the test class instance
        Object testInstance = result.getInstance();

        // Cast to BaseTest to access the WebDriver
        WebDriver driver = ((BaseTest) testInstance).driver;

        if (driver != null) {
            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {
                // Create screenshots folder if not exists
                Files.createDirectories(Paths.get("screenshots"));

                // Save screenshot with test method name
                String screenshotPath = "screenshots/" + result.getName() + ".png";
                Files.copy(screenshot.toPath(), Paths.get(screenshotPath));

                System.out.println("Screenshot saved at: " + screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Not used
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Not used
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Not used
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }

    @Override
    public void onStart(ITestContext context) {
        // Not used
    }

    @Override
    public void onFinish(ITestContext context) {
        // Not used
    }
}
