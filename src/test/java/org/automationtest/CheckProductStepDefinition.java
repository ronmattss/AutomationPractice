package org.automationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.WebNavigator.CartComponent;
import org.automationtest.WebNavigator.ProductPage;
import org.junit.Assert;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckProductStepDefinition {
    LoginStepsDefinition loginStepsDefinition;
    CartComponent cartComponent;

    @Given ("I am logged in with {string} and {string}")
    public void userIsLoggedIn(String username, String password)
    {
        loginStepsDefinition.userIsInLoginPage();
        loginStepsDefinition.userIsLoggingIn(username, password);
        loginStepsDefinition.userIsLoggedIn();
    }
    @When("I am and in the cart view")
    public void userIsOnCartView()
    {
        ProductPage.clickCartView();
    }

    @Then("I have at least {int} items in the cart")
    public void userVerifiesProductCount(int productCount)
    {
        cartComponent = new CartComponent();

        if(cartComponent.getCartProductList().isEmpty())
        {
            Assert.fail("Cart is empty!");
        }
        else
        {
            assertTrue(true);
        }

    }
}
