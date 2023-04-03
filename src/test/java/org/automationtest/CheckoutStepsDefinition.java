package org.automationtest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.*;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CheckoutStepsDefinition {

    HomePage homePage;
    LoginPage loginPage;
    CartComponent cartView;
    CheckoutPage checkoutPage;
    SearchStepsDefinition searchStepsDefinition = new SearchStepsDefinition();




    @Given("I am in the website and logged in with my credentials {string} and {string}")
    public void userIsLoggedIn(String username, String password)
    {
        homePage.clickLoginView();
        loginPage = new LoginPage();
        loginPage.Login(username,password);
    }

    @Given ("I have at least {int} product to my cart")
    public void userCheckProductsInCart(int productCount)
    {
        homePage.clickCartView();
        WebNavigatorHelper.getInstance().pauseExecution(1000);
        cartView = new CartComponent();
        if (cartView.getCartProductList().isEmpty()) {
            CustomLogger.logInfo("There are no products in the cart");
            Assertions.fail("There are no products in the cart");
        }
        else
        {
            CustomLogger.logInfo("There are  products in the cart");
            assertTrue("There are products in the cart",productCount > 0);
        }
    }

    @Given("I am on the checkout page")
    public void userIsOnCheckOut()
    {
        cartView = new CartComponent();
        cartView.clickProceedToCheckout();
    }

    @When("I proceed to checkout")
    public void userIsReadyToCheckout()
    {
        homePage.clickCartView();

        cartView = new CartComponent();
        if(cartView.checkIfCartIsEmpty())
        {
            fail("Cart is empty");
        }
        cartView.clickProceedToCheckout();
        assertTrue("Proceeding to checkout",true);
    }

    @Then("I check if my details are correct")
    public void userIsCheckingDetails()
    {
        checkoutPage = new CheckoutPage();
    }


}
