//package org.automation;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.automation.WebNavigator.LoginPage;
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import static org.junit.jupiter.api.Assertions.*;
//public class SearchStepsDefinition {
//
//    WebDriver browserDriver = new FirefoxDriver();
//    LoginPage loginPage;
//    @Given("User is logged in using valid credentials of {username} and password {password}")
//    public void userIsLoggedInUsingValidCredentials(String username, String password) {
//        // Use the login step that you've created before
//        // to log in with valid credentials
//        loginPage = new LoginPage(browserDriver,By.xpath("//input[@type='email']"),
//                By.xpath("//input[@type='password']"),By.xpath("//button[text()='Login']"));
//        loginPage.fillInUsername(username);
//        loginPage.filInPassword(password);
//        loginPage.clickLogin();
//    }
//
//    @When("^User navigates to the Products page$")
//    public void userNavigatesToTheProductsPage() {
//        // Navigate to the Products page using the browser driver
//        // or any other relevant code
//        browserDriver.navigate().to("https://www.automationexercise.com/products");
//    }
//
//    @When("^User searches for \"(.*)\" products$")
//    public void userSearchesForProducts(String productName) {
//        // Find the search field on the page and type the product name
//        // into it using the browser driver or any other relevant code
//        WebElement searchField = browserDriver.findElement(By.id("search_product"));
//        searchField.sendKeys(productName);
//        searchField.submit();
//    }
//
//    @Then("^User should be able to see the \"(.*)\" products on the search results page$")
//    public void userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage(String productName) {
//        // Check that the search results page contains the product name
//        // using the browser driver or any other relevant code
//        WebElement searchResults = browserDriver.findElement(By.id("search-results"));
//        assertTrue(searchResults.getText().contains(productName));
//
//        // to add things to the cart
//        // get the data-product-id of each result
//    }
//}
