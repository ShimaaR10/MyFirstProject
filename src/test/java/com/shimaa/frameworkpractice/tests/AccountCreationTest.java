package com.shimaa.frameworkpractice.tests;

import com.shimaa.frameworkpractice.base.BaseTest;
import com.shimaa.frameworkpractice.pages.HomePage;
import com.shimaa.frameworkpractice.pages.RegistrationPage;
import com.shimaa.frameworkpractice.pages.LoginPage;
import com.shimaa.frameworkpractice.utils.TestDataGenerator;
import com.shimaa.frameworkpractice.utils.TestDataManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class AccountCreationTest extends BaseTest{

    private HomePage home;
    private RegistrationPage registration;
    private LoginPage login;

    private String firstName;
    private String lastName;
    private String email;
    private String password;



    @BeforeClass
    public void setUpPages() {
        home = new HomePage(driver);
        registration = new RegistrationPage(driver);
        login = new LoginPage(driver);

        // Generate dynamic test data using TestDataGenerator
        firstName = TestDataGenerator.generateFirstName();
        lastName = TestDataGenerator.generateLastName();
        email = TestDataGenerator.generateEmail();
        password = TestDataGenerator.generatePassword();
    }

    @Test(description = "Verify that a new user can register and login successfully")
    public void testRegisterAndLogin() throws InterruptedException {

        // Navigate to Registration Page
        home.clickRegister();

        // Fill the registration form
        registration.fillRegistrationForm(firstName, lastName, email, "male", "MyCompany", password);


        // Submit the form
        registration.submitForm();

        // Verify registration success
        Assert.assertTrue(registration.isRegistrationSuccess(), "Registration failed!");

        // Store registered credentials in TestDataManager for other tests
        TestDataManager.registeredEmail = email;
        TestDataManager.registeredPassword = password;

        // Log out
        home.clickLogout();

        // Go to login page and log in
        home.clickLogin();
        login.login(email, password);

        // Verify login success
        Assert.assertTrue(login.isLoginSuccess(), "Login failed!");


    }


}
