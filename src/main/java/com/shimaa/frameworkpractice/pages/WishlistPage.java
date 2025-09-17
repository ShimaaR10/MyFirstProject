package com.shimaa.frameworkpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WishlistPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By addToWishlistBtn = By.cssSelector("button.add-to-wishlist-button");
    private final By wishlistLink = By.linkText("Wishlist");
    private final By wishlistProductNames = By.cssSelector(".wishlist-content td.product a");
    private final By wishlistItems = By.cssSelector(".wishlist-content tbody tr");
    private final By addToCartCheckbox = By.cssSelector("input[name='addtocart']");
    private final By addToCartButton = By.cssSelector("button[name='addtocartbutton']");
    private final By productNameLink = By.cssSelector("td.product a");

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }



    public void addToWishlist() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToWishlistBtn));
        btn.click();
    }

    public void openWishlist() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(wishlistLink));
        link.click();
    }

    // Verify if a product exists in the wishlist
    public boolean isProductInWishlist(String productName) {
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(wishlistProductNames));
        return products.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
    }


      //Select the checkbox for a given product in wishlist

    public void selectProductForCart(String productName) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(wishlistItems));
        for (WebElement row : rows) {
            WebElement nameElement = row.findElement(productNameLink);
            if (nameElement.getText().equalsIgnoreCase(productName)) {
                WebElement checkbox = row.findElement(addToCartCheckbox);
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
                break;
            }
        }
    }


    // Click Add to Cart button from wishlist
    public void clickAddToCartButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        btn.click();
    }

}
