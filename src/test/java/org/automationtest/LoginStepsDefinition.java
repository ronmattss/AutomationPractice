package org.automationtest;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.WebNavigator.LoginPage;
import org.automationtest.WebNavigator.ProductPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

public class LoginStepsDefinition  {

    WebDriver browserDriver = WebNavigatorHelper.getInstance().getBrowserDriver();
    LoginPage loginPage;




    @Given("I am on the website homepage")
    public void userIsInLoginPage()
    {

        browserDriver.get("https://www.automationexercise.com"); // this will go to the homepage
        browserDriver.manage().window().maximize();
        browserDriver.findElement(By.xpath("//a[@href='/login']")).click();
        loginPage = new LoginPage(browserDriver);
    }
    @When("I use my credentials {string} and {string}")
    public void userIsLoggingIn(String username, String password)
    {
        loginPage.fillInUsername(username);
        loginPage.filInPassword(password);
        loginPage.clickLogin();
        WebNavigatorHelper.getInstance().pauseExecution(500);

    }
    @Then("I am logged in and can see the products page")
    public  void userIsLoggedIn()
    {
         assertTrue(loginPage.verifyLogin());

    }



}
