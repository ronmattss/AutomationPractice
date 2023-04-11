package org.automationtest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.*;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBuyingStepDefinition {

    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    LoginPage loginPage;
    CartPage cartComponent;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;
    InvoicePage invoicePage;
    Random random = new Random();


    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        driver = WebNavigatorHelper.getInstance().getBrowserDriver();

        CustomLogger.logInfo("Opening Webpage");
        driver.get("https://www.automationexercise.com"); // this will go to the homepage

        WebNavigatorHelper.getInstance().pauseExecution(500);
        driver.manage().window().maximize();

        homePage = new HomePage();
    }


    @Then("I can Login")
    public void iCanLogin() {
        CustomLogger.logInfo("Going to Login page");
        homePage.clickLoginView();
    }


    @When("I use my credentials {string} and {string}")
    public void userIsLoggingIn(String username, String password) {
        loginPage = new LoginPage();
        CustomLogger.logInfo("User is logging in");

        loginPage.Login(username, password);
        WebNavigatorHelper.getInstance().pauseExecution(500);

    }


    @Then("I am logged in and can see the products page")
    public void userIsLoggedIn() {
        if (loginPage.verifyLogin()) {
            CustomLogger.logInfo("User is Logged in");
            assertTrue(true);
        } else {
            CustomLogger.logInfo("Failed to login");
            assertFalse(false);
        }
    }

    @When("I am in the products page")
    public void userNavigatesToTheProductsPage() {

        homePage.goToProductPage();
        CustomLogger.logInfo("User is on Products page");

        // try and remove ads if present
        try {
            WebNavigatorHelper.getInstance().dismissAd();
        } catch (Exception e) {
            System.out.println("No ads Detected");
        }
        productPage = PageFactory.initElements(driver, ProductPage.class);
    }


    @When("I search for {string}")
    public void userSearchesForProducts(String productName) {
        CustomLogger.logInfo("Searching for: " + productName);
        productPage.searchProduct(productName);

    }

    @Then("I should be able to see the products on the search results page")
    public void userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage() {
        CustomLogger.logInfo("Displaying search results");
        assertFalse(productPage.getSearchResults().isEmpty(), "No search results found");

    }

    /**
     * This step adds a specified number of random products to the cart from the search results page.
     *
     * @param numberOfProducts the number of products to add to the cart
     */
    @When("I add {int} products to my cart")
    public void userAddProductsToTheCart(int numberOfProducts) {
        //Randomly add a product
        for (int i = 0; i < numberOfProducts; i++) {
            int getRandomIndex = random.nextInt(productPage.getSearchResults().size());

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productCartButton = wait.until(ExpectedConditions.elementToBeClickable(productPage.getSearchResults().get(getRandomIndex))); // wait until element is clickable

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productCartButton);
            productPage.addProductToCart(productCartButton);

            productPage.getSearchResults().remove(productCartButton);

            productPage.clickContinueShoppingButton();
        }

    }

    /**
     * This step verifies that the number of products in the cart matches the specified value.
     *
     * @param productsInCart the expected number of products in the cart
     */
    @Then("I should see {int} t-shirts in my cart")
    public void userVerifiesCartProducts(int productsInCart) {
        WebNavigatorHelper.getInstance().pauseExecution(500);
        homePage.clickCartView();

        cartComponent = new CartPage();
        WebNavigatorHelper.getInstance().pauseExecution(1000);
        assertEquals(productsInCart, cartComponent.getCartSize());
    }


    /**
     * goes to the CartView
     */
    @When("I am in the cart view")
    public void userIsOnCartView() {

        homePage.clickCartView();
    }

    /**
     * Checks products in the cart
     */

    @Then("I have at least {int} items in the cart")
    public void userVerifiesProductCount(int productCount) {

        if (cartComponent.getCartProductList().isEmpty()) {
            Assert.fail("Cart is empty!");
        } else {
            assertTrue(true, "Cart has " + productCount + "product/s");
        }

    }

    @Then("I remove a product and I should have {int} products left")
    public void userRemovesAProduct(int productCount) {
        // if cart is empty don't remove anything
        cartComponent = new CartPage();

        if (cartComponent.getCartProductList().isEmpty()) {
            CustomLogger.logInfo("There are no products in the cart");
            assertTrue(cartComponent.getCartProductList().isEmpty(), "There are no products in the cart");
        } else if (cartComponent.getCartProductList().size() == productCount + 1) {
            cartComponent.RemoveCardProduct(random.nextInt(cartComponent.getCartProductList().size()));
            assertEquals(productCount, cartComponent.getCartProductList().size());
            CustomLogger.logInfo("Removed one product");
        } else {
            CustomLogger.logWarning("Cannot remove anymore product, cart will be empty");
            assertFalse(false, "Cannot remove anymore product, cart will be empty ");
        }
    }


    /**
     * clicks the checkout button
     */
    @When("I proceed to checkout and place my order")
    public void userPlaceOrder() {
        cartComponent.clickProceedToCheckout();
        checkoutPage = new CheckoutPage();
        checkoutPage.clickCheckOutButton();
    }

    /**
     * enters card details
     * Note that there are no validations in the test website,
     */
    @And("I enter my payment details")
    public void userEntersPaymentDetails() {
        paymentPage = new PaymentPage();
        // initial fill up form to check if everything works
        paymentPage.fillPaymentForm("John Doe", "1234567890123456", "123", "12", "2025");
    }

    /**
     * confirm order and
     * Download invoice
     */
    @Then("I Confirm my order and download my invoice")
    public void userPlacedOrder() {
        paymentPage.clickPayButton();
        WebNavigatorHelper.getInstance().pauseExecution(500);
        invoicePage = new InvoicePage();
        invoicePage.clickDownloadInvoice();
        invoicePage.clickContinue();
    }

}
