package com.shimaa.frameworkpractice.base;

import com.shimaa.frameworkpractice.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException {
        // Load properties file
        ConfigReader.loadConfig();

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // ChromeOptions for headless mode if running in CI
        ChromeOptions options = new ChromeOptions();
        if (System.getProperty("headless", "false").equals("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");
        }

        driver = new ChromeDriver(options);

        // Maximize only if not headless
        if (!System.getProperty("headless", "false").equals("true")) {
            driver.manage().window().maximize();
        }

        driver.manage().deleteAllCookies();

        // Get environment and URL from config
        String env = ConfigReader.get("env");
        String url = ConfigReader.get("url_" + env);
        driver.get(url);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
