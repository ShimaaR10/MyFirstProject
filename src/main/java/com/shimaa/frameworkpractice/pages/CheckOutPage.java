package com.shimaa.frameworkpractice.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckOutPage {

    private final WebDriver driver;
    private WebDriverWait wait;

    private final By countryDropDown = By.id("BillingNewAddress_CountryId");
    private final By stateDropDown = By.id("BillingNewAddress_StateProvinceId");
    private final By cityInput = By.id("BillingNewAddress_City");
    private final By addressInput = By.id("BillingNewAddress_Address1");
    private final By postalCodeInput = By.id("BillingNewAddress_ZipPostalCode");
    private final By phoneNumberInput = By.id("BillingNewAddress_PhoneNumber");
    private final By continueBtn = By.cssSelector("button.button-1.new-address-next-step-button[name='save']");
    private final By continueShippingMethod = By.cssSelector(".button-1.shipping-method-next-step-button");
    private final By continuePaymentMethod = By.xpath("//button[@class='button-1 payment-method-next-step-button']");
    private final By continuePaymentInfo = By.cssSelector("button.button-1.payment-info-next-step-button");
    private final By confirmOrder = By.cssSelector("button.button-1.confirm-order-next-step-button");
    private final By confirmationMessage = By.cssSelector(".section.order-completed .title strong");
    private final By ordersLink = By.linkText("Orders");
    private final By orderItems = By.cssSelector(".order-list .order-item");
    private final By orderNumber = By.cssSelector(".order-list .order-item .title strong");
    private final By orderNumberConfirmation = By.cssSelector(".order-number");
    private final By continueBillingBtn = By.cssSelector("button.new-address-next-step-button");

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Utility for extracting digits only ---
    private String normalizeOrderNumber(String text) {
        return text.replaceAll("[^0-9]", "").trim();
    }

    // --- Billing / Shipping details ---
    public void selectCountry(String country) {
        WebElement countryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropDown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", countryElement);
        new Select(countryElement).selectByVisibleText(country);
        wait.until(ExpectedConditions.elementToBeClickable(stateDropDown));
    }

    public void selectState(String state) {
        WebElement stateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(stateDropDown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateElement);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(stateDropDown, state));
        new Select(stateElement).selectByVisibleText(state);
    }

    public void cityInsert(String city) {
        WebElement cityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cityInput));
        cityElement.clear();
        cityElement.sendKeys(city);
    }

    public void address1Insert(String address) {
        WebElement addressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput));
        addressElement.clear();
        addressElement.sendKeys(address);
    }

    public void postalCodeInsert(String postalCode) {
        WebElement postalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput));
        postalElement.clear();
        postalElement.sendKeys(postalCode);
    }

    public void phoneNumberInsert(String phoneNumber) {
        WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberInput));
        phoneElement.clear();
        phoneElement.sendKeys(phoneNumber);
    }

    public void clickContinueBtn() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        try {
            btn.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    // --- Checkout steps ---

    public void clickContinueBillingAddress() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBillingBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        try {
            btn.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public void clickContinueShippingMethod() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShippingMethod)).click();
    }

    public void clickContinuePaymentMethod() {
        WebElement continueElement = wait.until(ExpectedConditions.elementToBeClickable(continuePaymentMethod));
        try {
            continueElement.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueElement);
        }
    }

    public void clickContinuePaymentInfo() {
        WebElement continueElement = wait.until(ExpectedConditions.elementToBeClickable(continuePaymentInfo));
        try {
            continueElement.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueElement);
        }
    }

   /* public void clickContinueConfirmOrder() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(confirmOrder));
        try {
            btn.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }*/

    public void clickContinueConfirmOrder() {
        // Wait for any overlay to disappear
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-overlay, .bar-notification")));

        // Wait for Confirm button to be clickable
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(confirmOrder));

        try {
            btn.click(); // try normal click
        } catch (ElementClickInterceptedException e) {
            // fallback: scroll and click via JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", btn);
        }

        // Wait for confirmation message
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
    }



    // --- Confirmation / Orders ---
    public String getOrderConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).getText();
    }

    public void goToOrdersPage() {
        wait.until(ExpectedConditions.elementToBeClickable(ordersLink)).click();
    }

    public boolean isAnyOrderListed() {
        List<WebElement> orders = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderItems));
        return !orders.isEmpty();
    }

    public String getOrderNumberFromConfirmation() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberConfirmation));
        return normalizeOrderNumber(element.getText());
    }

    public boolean isOrderListed(String orderNumberText) {
        List<WebElement> orders = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderNumber));
        for (WebElement order : orders) {
            String normalized = normalizeOrderNumber(order.getText());
            if (normalized.equals(orderNumberText)) {
                return true;
            }
        }
        return false;
    }
}
