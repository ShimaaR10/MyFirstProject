package com.shimaa.frameworkpractice.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

/**
 * BasePage is the parent class for all Page Objects.
 * It contains reusable methods for interacting with web elements,
 * applying explicit waits, and mimicking human-like pauses.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private final Random random = new Random();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait for element to be visible
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Click action with wait + human pause
    protected void click(By locator) {
        waitForClickable(locator).click();
        humanPause();
    }

    // Type action with wait + human pause
    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
        humanPause();
    }

    // Get text from element
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    // Check if element is displayed
    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Random pause to mimic human behavior
    protected void humanPause() {
        try {
            Thread.sleep(500 + random.nextInt(1500)); // 0.5–2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
