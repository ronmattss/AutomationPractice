package org.automationtest.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver browserDriver;
    private WebElement userField;
    private WebElement passwordField;
    private WebElement submitButton;
    public LoginPage (WebDriver driver)
    {
        browserDriver = driver;
        userField = browserDriver.findElement( By.xpath("//input[@type='email'][@data-qa='login-email'][@name='email']"));
        passwordField = browserDriver.findElement(By.xpath("//input[@type='password']"));
        submitButton = browserDriver.findElement(By.xpath("//button[text()='Login']"));

    }



    public void fillInUsername(String username)
    {
        userField.sendKeys(username);
    }
    public void filInPassword(String password)
    {
        passwordField.sendKeys(password);
    }

    public void clickLogin()
    {
        submitButton.click();
    }

    // Change all xPath
    public boolean verifyLogin()
    {
        try {
            String loginUsername = browserDriver.findElement(By.xpath("//a[contains(text(), 'Logged in as')]//b")).getText();
            System.out.println("Logged in as "+ loginUsername);
            return browserDriver.findElement(By.xpath("//a[contains(text(), 'Logged in as')]//b")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
