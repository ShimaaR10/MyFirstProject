package com.shimaa.frameworkpractice.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SearchResultsPage {

    private final WebDriver driver;
    private WebDriverWait wait;


    // Locators
    private final By sortDropdown = By.id("products-orderby");
    private final By firstProductLink = By.cssSelector(".product-item .product-title a");
    private final By ajaxOverlay = By.cssSelector(".ajax-loading-block-window"); // if site uses overlay
    private final String productLinkXpathPattern = "//a[text()='%s']";
    private final By displayDropdown = By.id("products-pagesize");
    private final By addToCartButton = By.cssSelector(".button-1.add-to-cart-button");
    private final By addToCartSuccessMessage = By.cssSelector(".bar-notification.success");
    private final By addToCartCloseMessage = By.cssSelector(".bar-notification.success.close");


    public  SearchResultsPage (WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    // Actions
    public void applySortDropdown() {
        WebElement sortElement = wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
        Select sortDropdownSelect = new Select(sortElement);
        sortDropdownSelect.selectByIndex(1);

    }

    public void applyDispalyDropdown() {
        WebElement displayElement = wait.until(ExpectedConditions.visibilityOfElementLocated(displayDropdown));
        displayElement.click();
        displayElement.sendKeys("9");

    }

    public void selectFirstProduct() {

        // Wait for Ajax overlay to disappear (if any)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxOverlay));

        // Retry clicking in case of stale element
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductLink));
                wait.until(ExpectedConditions.elementToBeClickable(firstProductLink)).click();
                break; // success
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
            }
        }
    }

    public void selectProductByName(String productName) {
        By productLink = By.xpath(String.format("//a[text()='%s']", productName));
        int attempts = 0;

        while (attempts < 3) {
            try {
                // Find the element fresh every attempt
                WebElement product = wait.until(ExpectedConditions.elementToBeClickable(productLink));

                // Scroll into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);

                // Try click normally
                product.click();

                // Optional: wait until URL contains product name
                wait.until(ExpectedConditions.urlContains(productName.toLowerCase().replace(" ", "-")));

                return; // success, exit
            } catch (org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.ElementClickInterceptedException e) {
                attempts++;
                System.out.println("Retry clicking product, attempt " + attempts);
            }
        }

        throw new RuntimeException("Failed to click product: " + productName);
    }

    public void addToCart(){
        // Wait for possible overlays
        By overlay = By.cssSelector(".loading-mask");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        button.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartSuccessMessage));
    }

    public void verifyAndCloseAddToCartMessage()
    {
        // Wait for success message to appear
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartSuccessMessage));
        Assert.assertTrue(message.getText().contains("The product has been added"),
                "Add to cart message not displayed or incorrect!");

        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartCloseMessage));

                // Scroll into view just in case
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeBtn);

                // Try normal click first
                closeBtn.click();

                // Wait until the notification disappears
                wait.until(ExpectedConditions.invisibilityOf(closeBtn));
                return; // success
            } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
                System.out.println("Retry closing Add to Cart message, attempt " + attempts);
            }
        }
        throw new RuntimeException("Failed to close Add to Cart message!");
    }
}
