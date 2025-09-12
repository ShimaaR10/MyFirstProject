package com.shimaa.frameworkpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {


    private final WebDriver driver;


    // ===== Locators =====
    private final By maleRadio = By.id("gender-male");
    private final By femaleRadio = By.id("gender-female");
    private final By firstNameField = By.id("FirstName");
    private final By lastNameField = By.id("LastName");
    private final By emailField = By.id("Email");
    private final By companyField = By.id("Company");
    private final By passwordField = By.id("Password");
    private final By confirmPasswordField = By.id("ConfirmPassword");
    private final By registerButton = By.id("register-button");
    private final By successMessage = By.cssSelector(".result");


    private WebDriverWait wait;

    // ===== Constructor =====
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void fillRegistrationForm(String firstName, String lastName, String email,
                                     String gender, String company, String password) {

        // Gender selection
        if (gender.equalsIgnoreCase("male")) {
            wait.until(ExpectedConditions.elementToBeClickable(maleRadio)).click();
        } else if (gender.equalsIgnoreCase("female")) {
            wait.until(ExpectedConditions.elementToBeClickable(femaleRadio)).click();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(companyField)).sendKeys(company);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField)).sendKeys(password);
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public boolean isRegistrationSuccess() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
