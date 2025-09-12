package com.shimaa.frameworkpractice.base;

import com.shimaa.frameworkpractice.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException {
        // Load properties file
        ConfigReader.loadConfig();

        // Setup ChromeDriver first
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //Implicit wait

        // Get env and url from config
        String env = ConfigReader.get("env");
        String url = ConfigReader.get("url_" + env); // e.g. url_test or url_prod
        driver.get(url); // ✅ now driver is initialized
    }

  /*  @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

   */
}
