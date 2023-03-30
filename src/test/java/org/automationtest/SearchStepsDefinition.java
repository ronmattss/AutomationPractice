package org.automationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.automationtest.WebNavigator.LoginPage;
import org.automationtest.WebNavigator.ProductPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


import static org.junit.jupiter.api.Assertions.*;
public class SearchStepsDefinition {

    WebDriver browserDriver = new FirefoxDriver();
    LoginPage loginPage;
    ProductPage productPage;
    @Given("User is logged in using valid credentials of {string} and password {string}")
    public void userIsLoggedInUsingValidCredentials(String username, String password) {
        browserDriver.get("https://www.automationexercise.com"); // this will go to the homepage
        browserDriver.findElement(By.xpath("//a[@href='/login']")).click();

        loginPage = new LoginPage(browserDriver,By.xpath("//input[@type='email']"),
                By.xpath("//input[@type='password']"),By.xpath("//button[text()='Login']"));

        loginPage.fillInUsername(username);
        loginPage.filInPassword(password);
        loginPage.clickLogin();
    }

    @When("User navigates to the Products page")
    public void userNavigatesToTheProductsPage() {
        browserDriver.findElement(By.xpath("//a[@href='/products']")).click();

        // Why is there an ad?
        System.out.println(browserDriver.switchTo().defaultContent().getTitle());
        // try and remove ads if present
        try{
            WebNavigatorHelper.getInstance().dismissAd(browserDriver);
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("No ads Detected");
        }
        productPage = new ProductPage(browserDriver,By.xpath("//input[@id='search_product']"),
                By.xpath("//button[@id='submit_search']"));

    }

    @When("User searches for {string} products")
    public void userSearchesForProducts(String productName) {

        productPage.searchProduct(productName);
    }

    //
    @Then("User should be able to see the {string} products on the search results page")
    public void userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage(String productName) {

        productPage.setSearchResults(By.xpath("//div[@class='productinfo text-center']//a[@data-product-id]"));

        for(int i = 0;i<productPage.getSearchResults().size();i++)
        {
            String product = productPage.getSearchResults().get(i).findElement(By.xpath("//preceding-sibling::p")).getText().toLowerCase();

            if(product.contains("t-shirt") || product.contains("tshirt")){
                product = "tshirt";
                assertEquals(productName, product);
            }
        }


        // to add things to the cart
        // get the data-product-id of each result
    }
}
