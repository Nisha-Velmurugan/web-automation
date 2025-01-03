package com.example.automation;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginAutomationTest {
    @Test
    public void testLogin() {
        // Set up the WebDriver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        try {
            // Navigate to Form Authentication page
            driver.get("https://the-internet.herokuapp.com/login");

            // Locate the username and password fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            // Perform login
            usernameField.sendKeys("tomsmith");  // Valid username
            passwordField.sendKeys("SuperSecretPassword!");  // Valid password
            loginButton.click();

            // Validate successful login (you can check the page title or a specific element)
            String expectedTitle = "The Internet";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

