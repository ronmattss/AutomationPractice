package org.automationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.automationtest.WebNavigator.HomePage;
import org.automationtest.WebNavigator.LoginPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;

public class HomepageStepDefinition {

    LoginPage loginPage;
    HomePage homePage;
    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        WebNavigatorHelper.getInstance().getBrowserDriver().get("https://www.automationexercise.com"); // this will go to the homepage
        WebNavigatorHelper.getInstance().getBrowserDriver().manage().window().maximize();
        homePage = new HomePage();
    }

    @Then("I can Login")
    public void iCanLogin() {
        homePage.clickLoginView();
    }
}
