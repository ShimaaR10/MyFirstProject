package com.shimaa.frameworkpractice.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {


    private final WebDriver driver;
    private WebDriverWait wait;


    // Locators
    private final By emailInput = By.id("Email");
    private final By passwordInput = By.id("Password");
    private final By loginButton = By.xpath("//button[text()='Log in']");
    private final By myAccountButton = By.linkText("My account");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }


    public boolean isLoginSuccess() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountButton));
            return driver.findElement(myAccountButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
