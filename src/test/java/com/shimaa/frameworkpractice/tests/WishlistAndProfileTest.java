package com.shimaa.frameworkpractice.tests;

import com.shimaa.frameworkpractice.base.BaseTest;
import com.shimaa.frameworkpractice.utils.TestDataManager;
import com.shimaa.frameworkpractice.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WishlistAndProfileTest extends BaseTest {

    private HomePage home;
    private LoginPage login;
    private SearchResultsPage searchResultPage;
    private WishlistPage wishlistPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckOutPage checkoutPage;
    private CustomerInfoPage customerInfoPage;

    private String registeredEmail;
    private String registeredPassword;

    @BeforeClass
    public void setUpPages() {
        home = new HomePage(driver);
        login = new LoginPage(driver);
        searchResultPage = new SearchResultsPage(driver);
        wishlistPage = new WishlistPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckOutPage(driver);
        customerInfoPage = new CustomerInfoPage(driver);

        registeredEmail = TestDataManager.registeredEmail;
        registeredPassword = TestDataManager.registeredPassword;
    }


    @Test(description = "Wishlist & Profile management flow")
    public void testWishlistAndProfileManagement() {

        // 1. Log in
        home.clickLogin();
        login.login(registeredEmail, registeredPassword);
        Assert.assertTrue(login.isLoginSuccess(), "Login failed!");

        // 2. Search and add product to wishlist
        home.searchProduct("Laptop");
        searchResultPage.selectProductByName("Asus Laptop");
        wishlistPage.addToWishlist();

        // 3. Open wishlist & verify
        wishlistPage.openWishlist();
        Assert.assertTrue(wishlistPage.isProductInWishlist("Asus Laptop"), "Product not found in wishlist!");

        // 4. Move item from wishlist → cart
        wishlistPage.selectProductForCart("Asus Laptop");
        wishlistPage.clickAddToCartButton();
        shoppingCartPage.clickShoppingCart();
        shoppingCartPage.checkTermsOfService();

        //Proceed to Checkout
        shoppingCartPage.clickCheckOutButton();
        checkoutPage.clickContinueBillingAddress();

        //Shipping Method
        checkoutPage.clickContinueShippingMethod();

        //Payment Method
        checkoutPage.clickContinuePaymentMethod();

        //Payment Info
        checkoutPage.clickContinuePaymentInfo();

        // Confirm Order
        checkoutPage.clickContinueConfirmOrder();

        // Confirmation Message
        Assert.assertTrue(checkoutPage.getOrderConfirmationMessage().contains("successfully processed"), "Order confirmation message not displayed!");

       // Go to My Account → Customer info
        home.goToMyAccount();
        customerInfoPage.goToCustomerInfo();

        // Update details (example: change password)
        String newPassword = "Password123!Updated";
        customerInfoPage.changePassword(registeredPassword, newPassword);

        // Verify confirmation
        Assert.assertTrue(customerInfoPage.isPasswordChangeSuccess(),
                "Password change confirmation not displayed!");

        // Log out & log in with new password
        home.clickLogout();
        home.clickLogin();
        login.login(registeredEmail, newPassword);
        Assert.assertTrue(login.isLoginSuccess(), "Re-login with new password failed!");





    }


}