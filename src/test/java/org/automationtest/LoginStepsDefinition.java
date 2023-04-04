package org.automationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.HomePage;
import org.automationtest.WebNavigator.LoginPage;
import org.automationtest.WebNavigator.ProductPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;

import static org.junit.jupiter.api.Assertions.*;

public class LoginStepsDefinition  {

    LoginPage loginPage;

    /**
     * Part of the recurring Scenario
     * Logs in the user
     */

    /**
     * Logs in the user with
     * @param username the username of the user
     * @param password the password of the user
     */
    @When("I use my credentials {string} and {string}")
    public void userIsLoggingIn(String username, String password)
    {
        loginPage = new LoginPage();
        CustomLogger.logInfo("User is logging in");

        loginPage.Login(username,password);
        WebNavigatorHelper.getInstance().pauseExecution(500);

    }

    /**
     * verifies the login
     *
     */
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
