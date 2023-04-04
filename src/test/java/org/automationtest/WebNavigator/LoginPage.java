package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {


    private WebElement userField;
    private WebElement passwordField;
    private WebElement submitButton;

    public LoginPage ()
    {
        userField = WebNavigatorHelper.getInstance().getBrowserDriver().findElement( By.xpath("//input[@type='email'][@data-qa='login-email'][@name='email']"));
        passwordField = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//input[@type='password']"));
        submitButton = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//button[text()='Login']"));
    }


    // Form methods
    public void fillInUsername(String username)
    {
        userField.sendKeys(username);
    }
    public void fillInPassword(String password)
    {
        passwordField.sendKeys(password);
    }

    public void clickLogin()
    {
        submitButton.click();
    }

    /**
     * verifies the login of the user
     */

    public static boolean verifyLogin()
    {
        try {
            String loginUsername = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[contains(text(), 'Logged in as')]//b")).getText();
            System.out.println("Logged in as "+ loginUsername);
            return WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[contains(text(), 'Logged in as')]//b")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    /**
     * fills the form and clicks the button for the login
     */
    public void Login(String username,String password)
    {
        fillInUsername(username);
        fillInPassword(password);
        clickLogin();
    }

}
