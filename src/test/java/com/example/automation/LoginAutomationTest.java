package com.example.automation;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAutomationTest {
    @Test
    public void testLogin() {
        // Use WebDriverManager to handle ChromeDriver setup
        WebDriverManager.chromedriver().setup();

        // Set up Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode for CI environments
        options.addArguments("--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");

        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the login page
            driver.get("https://the-internet.herokuapp.com/login");

            // Locate the username, password fields, and login button
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            // Enter valid credentials and click login
            usernameField.sendKeys("tomsmith");
            passwordField.sendKeys("SuperSecretPassword!");
            loginButton.click();

            // Verify successful login by checking the presence of a success message
            WebElement successMessage = driver.findElement(By.cssSelector(".flash.success"));
            assertTrue(successMessage.isDisplayed(), "Login failed: Success message not displayed.");

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
