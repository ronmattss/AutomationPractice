package org.automation.WebNavigator;

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
    }

    public LoginPage(WebDriver driver, By userFieldElement, By passwordFieldElement, By submitButtonElement) {
        browserDriver = driver;
        userField = browserDriver.findElement(userFieldElement);
        passwordField = browserDriver.findElement(passwordFieldElement);
        submitButton = browserDriver.findElement(submitButtonElement);
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
