package org.automationtest;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.automationtest.WebNavigator.LoginPage;
import org.automationtest.WebNavigator.ProductPage;
import org.automationtest.WebNavigator.utils.WebDriverManager;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


import static org.junit.jupiter.api.Assertions.*;

public class SearchStepsDefinition {


    LoginStepsDefinition loginSteps = new LoginStepsDefinition();

    ProductPage productPage;


    @Before
    public void setUp(Scenario scenario) {
        WebDriverManager.getDriver();
    }

    @Given("I logged in using {string} and password {string}")
    public void userIsLoggedInUsingValidCredentials(String username, String password) {
        loginSteps.userIsInLoginPage();
        loginSteps.userIsLoggingIn(username, password);
        loginSteps.userIsLoggedIn();
    }

    @When("I  navigate to the Products page")
    public void userNavigatesToTheProductsPage() {
        WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/products']")).click();
        // Why is there an ad?
        System.out.println(WebNavigatorHelper.getInstance().getBrowserDriver().switchTo().defaultContent().getTitle());
        // try and remove ads if present
        try {
            WebNavigatorHelper.getInstance().dismissAd();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("No ads Detected");
        }
        productPage = new ProductPage();
    }

    @When("I search for {string}")
    public void userSearchesForProducts(String productName) {
        productPage.searchProduct(productName);
    }

    //
    @Then("I should be able to see the products on the search results page")
    public void userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage() {
        assertFalse(productPage.getSearchResults().isEmpty());
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriverManager.quitDriver();
    }


}
