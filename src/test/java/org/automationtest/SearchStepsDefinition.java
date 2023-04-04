package org.automationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.ProductPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.*;


import static org.junit.jupiter.api.Assertions.*;

public class SearchStepsDefinition {
    ProductPage productPage;


/**
 * Navigates to the Products page
 *
 *
 */

    @When("I am in the products page")
    public void userNavigatesToTheProductsPage() {

        WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/products']")).click();

        CustomLogger.logInfo("User is on Products page");
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

    /**
     * Searches for a product
     * @param  productName is what the method will look for
     *
     */
    @When("I search for {string}")
    public void userSearchesForProducts(String productName) {
        CustomLogger.logInfo("Searching for: "+ productName);

        productPage.searchProduct(productName);
    }

    /**
     * Checks to see if the product is displayed in the result     *
     * AssertingFalse if there are results being displayed
     */
    @Then("I should be able to see the products on the search results page")
    public void userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage() {
        CustomLogger.logInfo("Displaying search results");

        // Check that the search results list is not empty
        // If it is empty, the test will fail with the specified error message
        assertTrue(!productPage.getSearchResults().isEmpty(), "No search results found");

    }




}
