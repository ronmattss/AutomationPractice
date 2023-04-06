package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    @FindBy(xpath = "//input[@type='email'][@data-qa='login-email'][@name='email']")
    private WebElement userField;
    @FindBy(xpath = "//input[@type='password']")

    private WebElement passwordField;
    @FindBy(xpath = "//button[text()='Login']")

    private WebElement submitButton;
    @FindBy(xpath = "//a[contains(text(), 'Logged in as')]//b")
    private  WebElement loginUsername;


    public LoginPage ()
    {
        PageFactory.initElements(WebNavigatorHelper.getInstance().getBrowserDriver(), this);

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

    public  boolean verifyLogin()
    {
        try {
            return loginUsername.isDisplayed();
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
