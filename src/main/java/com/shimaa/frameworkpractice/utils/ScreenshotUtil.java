package com.shimaa.frameworkpractice.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String dest = "target/screenshots/" + testName + "_" + timestamp + ".png";

            // Create folder if it doesn't exist
            Files.createDirectories(Paths.get("target/screenshots"));

            Files.copy(srcFile.toPath(), Paths.get(dest));
            System.out.println("Screenshot saved: " + dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
