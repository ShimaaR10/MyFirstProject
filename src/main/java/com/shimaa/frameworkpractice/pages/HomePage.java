package com.shimaa.frameworkpractice.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private WebDriverWait wait;

    private final By registerLink = By.xpath("//a[text()='Register']");
    private final By loginLink = By.xpath("//a[text()='Log in']");
    private final By logoutLink = By.xpath("//a[text()='Log out']");
    private final By searchBox = By.id("small-searchterms");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By myAccountLink = By.xpath("//a[text()='My account']");
    private final By notificationBar = By.cssSelector(".bar-notification.success");
    private final By closeNotificationBtn = By.cssSelector(".bar-notification.success .close");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void clickRegister() {
        safeClick(registerLink);
    }

    public void clickLogin() {
        safeClick(loginLink);
    }

    public void clickLogout() {
        safeClick(logoutLink);
    }

    public void searchProduct(String productName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchInput.clear();
        searchInput.sendKeys(productName);
        safeClick(searchButton);
    }

    public void goToMyAccount() {
        safeClick(myAccountLink);
    }

    public void closeNotificationIfPresent() {
        try {
            WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBar));
            WebElement closeBtn = notification.findElement(closeNotificationBtn);
            closeBtn.click();
            wait.until(ExpectedConditions.invisibilityOf(notification));
        } catch (TimeoutException e) {
            // ignore if not present
        }
    }
}
