package com.shimaa.frameworkpractice.tests;

import com.shimaa.frameworkpractice.base.BaseTest;
import com.shimaa.frameworkpractice.utils.TestDataGenerator;
import com.shimaa.frameworkpractice.utils.TestDataManager;
import com.shimaa.frameworkpractice.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WishlistAndProfileTest extends BaseTest {

    private HomePage home;
    private RegistrationPage registration;
    private LoginPage login;
    private SearchResultsPage searchResultPage;
    private WishlistPage wishlistPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckOutPage checkoutPage;
    private CustomerInfoPage customerInfoPage;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String newPassword;

    @BeforeClass
    public void setUpPages() {
        home = new HomePage(driver);
        registration = new RegistrationPage(driver);
        login = new LoginPage(driver);
        searchResultPage = new SearchResultsPage(driver);
        wishlistPage = new WishlistPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckOutPage(driver);
        customerInfoPage = new CustomerInfoPage(driver);

        // Generate dynamic test data using TestDataGenerator
        firstName = TestDataGenerator.generateFirstName();
        lastName = TestDataGenerator.generateLastName();
        email = TestDataGenerator.generateEmail();
        password = TestDataGenerator.generatePassword();
        newPassword = TestDataGenerator.generateUpdatedPassword(password);
    }


    @Test(description = "Wishlist & Profile management flow")
    public void testWishlistAndProfileManagement() {


        // Navigate to Registration Page
        home.clickRegister();

        // Fill the registration form
        registration.fillRegistrationForm(firstName, lastName, email, "male", "MyCompany", password);


        // Submit the form
        registration.submitForm();

        // Verify registration success
        Assert.assertTrue(registration.isRegistrationSuccess(), "Registration failed!");


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

        // Confirmation Message
        Assert.assertTrue(checkoutPage.getOrderConfirmationMessage().contains("successfully processed"), "Order confirmation message not displayed!");

       // Go to My Account → Customer info
        home.goToMyAccount();
        customerInfoPage.goToCustomerInfo();

        // Update details (change password)
        customerInfoPage.changePassword(password, newPassword);


        // Verify confirmation
        Assert.assertTrue(customerInfoPage.isPasswordChangeSuccess(), "Password change confirmation not displayed!");

        //Close notification before logout
        home.closeNotificationIfPresent();

        // Log out & log in with new password
        home.clickLogout();
        home.clickLogin();
        login.login(email, newPassword);
        Assert.assertTrue(login.isLoginSuccess(), "Re-login with new password failed!");





    }


}