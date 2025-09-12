package com.shimaa.frameworkpractice.tests;

import com.shimaa.frameworkpractice.base.BaseTest;
import com.shimaa.frameworkpractice.utils.TestDataManager;
import com.shimaa.frameworkpractice.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PurchaseFlowTest extends BaseTest {


    private HomePage home;
    private LoginPage login;
    private CheckOutPage checkoutPage;
    private SearchResultsPage searchResultPage;
    private ShoppingCartPage shoppingCartPage;

    private String registeredEmail;
    private String registeredPassword;

@BeforeClass
    public void setUpPages()
    {
        home = new HomePage(driver);
        login = new LoginPage(driver);
        searchResultPage = new SearchResultsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckOutPage(driver);

        registeredEmail = TestDataManager.registeredEmail;
        registeredPassword = TestDataManager.registeredPassword;
    }


    @Test(description = "End-to-end purchase flow")
    public void testPurchaseFlow() {


        // Go to login page and log in
        home.clickLogin();
        login.login(registeredEmail, registeredPassword);

        // Verify login success
        Assert.assertTrue(login.isLoginSuccess(), "Login failed!");

        // Search for a product
        home.searchProduct("Laptop");

        //Apply Sort
        searchResultPage.applySortDropdown();
        searchResultPage.applyDispalyDropdown();

        //Open Product Details
        searchResultPage.selectProductByName("Asus Laptop");


        //Add Product to Cart
        searchResultPage.addToCart();
        //searchResultPage.verifyAndCloseAddToCartMessage();

        //Go to cart → update quantity → accept terms
        shoppingCartPage.clickShoppingCart();
        shoppingCartPage.clickLevelUpQuantity();
        shoppingCartPage.checkTermsOfService();

        //Proceed to Checkout
        shoppingCartPage.clickCheckOutButton();

        //Fill in billing/shipping details
        checkoutPage.selectCountry(TestDataManager.country);
        checkoutPage.selectState(TestDataManager.state);
        checkoutPage.cityInsert(TestDataManager.city);
        checkoutPage.address1Insert(TestDataManager.address1);
        checkoutPage.postalCodeInsert(TestDataManager.postalCode);
        checkoutPage.phoneNumberInsert(TestDataManager.phoneNumber);
        checkoutPage.clickContinueBtn();

        //Shipping Method
        checkoutPage.clickContinueShippingMethod();

        //Payment Method
        checkoutPage.clickContinuePaymentMethod();

        //Payment Info
        checkoutPage.clickContinuePaymentInfo();

        // Confirm Order
        checkoutPage.clickContinueConfirmOrder();

        // Capture order number
        String orderNumber =checkoutPage.getOrderNumberFromConfirmation();

        // Confirmation Message
        Assert.assertTrue(checkoutPage.getOrderConfirmationMessage().contains("successfully processed"),
                "Order confirmation message not displayed!");

        // Go To My Account
        home.goToMyAccount();

        // Go To Order
        checkoutPage.goToOrdersPage();

        // Verify Order
        Assert.assertTrue(checkoutPage.isAnyOrderListed(), "No orders were found!");



        // Verify order is listed
        Assert.assertTrue(checkoutPage.isOrderListed(orderNumber), "Order #" + orderNumber + " was not found in Orders page!");



    }


    }
