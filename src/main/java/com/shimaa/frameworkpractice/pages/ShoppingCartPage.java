package com.shimaa.frameworkpractice.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By shoppingCartLink = By.cssSelector(".ico-cart");
    private final By levelUpQuantity = By.cssSelector("div.quantity.up"); // dynamic ID handled
    private final By termOfServiceButton = By.id("termsofservice");
    private final By checkOutButton = By.id("checkout");

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click on the shopping cart link
    public void clickShoppingCart() {
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
        try {
            cartLink.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartLink);
        }
    }

    // Increase quantity for the first product in the cart
    public void clickLevelUpQuantity() {
        WebElement upButton = wait.until(ExpectedConditions.elementToBeClickable(levelUpQuantity));
        try {
            upButton.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", upButton);
        }
    }

    // Click terms of service checkbox
    public void checkTermsOfService() {
        WebElement terms = wait.until(ExpectedConditions.visibilityOfElementLocated(termOfServiceButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", terms);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(terms)).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", terms);
        }
    }

    // Click the checkout button
    public void clickCheckOutButton() {
        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkOutButton));
        try {
            checkout.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkout);
        }
    }
}
