package com.shimaa.frameworkpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
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

    public void clickRegister() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try { element.click(); } catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element); }
    }

    public void clickLogin() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try { element.click(); } catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element); }
    }

    public void clickLogout() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try { element.click(); } catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element); }
    }

    public void searchProduct(String productName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchInput.clear();
        searchInput.sendKeys(productName);

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchBtn);
        try { searchBtn.click(); } catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn); }
    }

    public void goToMyAccount() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(myAccountLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try { element.click(); } catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element); }
    }

    public void closeNotificationIfPresent() {
        try {
            WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationBar));
            WebElement closeBtn = notification.findElement(closeNotificationBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeBtn);
            try { closeBtn.click(); } catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn); }
            wait.until(ExpectedConditions.invisibilityOf(notification));
        } catch (TimeoutException e) {
        }
    }
}
