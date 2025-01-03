package com.example.myautomation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    } 
    public void enterUsername(String username) {
        WebElement usernameFieldElement = driver.findElement(usernameField);
        usernameFieldElement.sendKeys(username);
    } 
    public void enterPassword(String password) {
        WebElement passwordFieldElement = driver.findElement(passwordField);
        passwordFieldElement.sendKeys(password);
    }
    public void clickLogin() {
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }
    public String getPageTitle() {
        return driver.getTitle();
    }
}