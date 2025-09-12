package com.shimaa.frameworkpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerInfoPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By customerInfoLink = By.linkText("Customer info");
    private final By changePasswordLink = By.linkText("Change password");
    private final By oldPasswordInput = By.id("OldPassword");
    private final By newPasswordInput = By.id("NewPassword");
    private final By confirmPasswordInput = By.id("ConfirmNewPassword");
    private final By changePasswordBtn = By.cssSelector("button.change-password-button");
    private final By passwordChangeSuccessMsg = By.cssSelector(".bar-notification.success");

    public CustomerInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToCustomerInfo() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(customerInfoLink));
        link.click();
    }

    public void changePassword(String oldPassword, String newPassword) {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(changePasswordLink));
        link.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(oldPasswordInput)).sendKeys(oldPassword);
        driver.findElement(newPasswordInput).sendKeys(newPassword);
        driver.findElement(confirmPasswordInput).sendKeys(newPassword);

        driver.findElement(changePasswordBtn).click();
    }

    public boolean isPasswordChangeSuccess() {
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordChangeSuccessMsg));
        return success.getText().toLowerCase().contains("password was changed");
    }
}
