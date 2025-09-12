package com.shimaa.frameworkpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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



    public HomePage (WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }


    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void searchProduct(String productName) {
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchInput.clear();
        searchInput.sendKeys(productName);

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }


    public void goToMyAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(myAccountLink)).click();
    }


}
