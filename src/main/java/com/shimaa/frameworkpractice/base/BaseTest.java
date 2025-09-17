package com.shimaa.frameworkpractice.base;

import com.shimaa.frameworkpractice.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait; // Add WebDriverWait for explicit waits

    @BeforeClass
    public void setUp() throws IOException {
        // Load properties file
        ConfigReader.loadConfig();

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Read headless property
        String headlessProp = System.getProperty("headless", "false").toLowerCase();

        // ChromeOptions configuration
        ChromeOptions options = new ChromeOptions();
        if (headlessProp.equals("true")) {
            options.addArguments("--headless=new");       // Headless mode
            options.addArguments("--no-sandbox");         // Linux CI fix
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080"); // Ensure full HD viewport
        }

        driver = new ChromeDriver(options);

        // Maximize browser window if not headless
        if (!headlessProp.equals("true")) {
            driver.manage().window().maximize();
        }

        driver.manage().deleteAllCookies();

        // Setup a default explicit wait of 15 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Get environment and URL from config
        String env = ConfigReader.get("env");
        String url = ConfigReader.get("url_" + env); // e.g., url_test or url_prod
        driver.get(url); // ✅ driver initialized
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
