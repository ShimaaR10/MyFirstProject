package com.shimaa.frameworkpractice.utils;

public class TestDataGenerator {

    // Generate unique email
    public static String generateEmail() {
        return "testuser" + System.currentTimeMillis() + "@gmail.com";
    }

    // Generate password
    public static String generatePassword() {
        return "Password123!";
    }

    // Generate first name
    public static String generateFirstName() {
        return "Test";
    }

    // Generate last name
    public static String generateLastName() {
        return "User";
    }
}

