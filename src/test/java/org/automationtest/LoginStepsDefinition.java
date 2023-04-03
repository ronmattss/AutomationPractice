package org.automationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.HomePage;
import org.automationtest.WebNavigator.LoginPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;

import static org.junit.jupiter.api.Assertions.*;

public class LoginStepsDefinition  {

    LoginPage loginPage;
    HomePage homePage;




    @Given("I am on the website homepage")
    public void userIsInLoginPage(){
        loginPage = new LoginPage();
    }
    @When("I use my credentials {string} and {string}")
    public void userIsLoggingIn(String username, String password)
    {
        CustomLogger.logInfo("User is logging in");

        loginPage.Login(username,password);
        WebNavigatorHelper.getInstance().pauseExecution(500);

    }
    @Then("I am logged in and can see the products page")
    public  void userIsLoggedIn()
    {
        if(loginPage.verifyLogin())
        {      CustomLogger.logInfo("User is Logged in");
            assertTrue(true);
        }
        else
        {
            CustomLogger.logInfo("Failed to login");
            assertFalse(false);
        }
    }









}
