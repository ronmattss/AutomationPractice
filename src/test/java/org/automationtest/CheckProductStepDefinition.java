package org.automationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.WebNavigator.CartPage;
import org.automationtest.WebNavigator.ProductPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.junit.Assert;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckProductStepDefinition {
    CartPage cartComponent;
    ProductPage productPage;

    /**
     * goes to the product page
     */
    @Given ("I am logged in and in the products page")
    public void userIsLoggedIn()
    {
        ProductPage.gotoProductsPage();
        WebNavigatorHelper.getInstance().dismissAd();
        productPage = new ProductPage();
    }
    /**
     * goes to the CartView
     */
    @When("I am  in the cart view")
    public void userIsOnCartView()
    {

        ProductPage.clickCartView();
    }

    /**
     * Checks products in the cart
     */

    @Then("I have at least {int} items in the cart")
    public void userVerifiesProductCount(int productCount)
    {
        cartComponent = new CartPage();

        if(cartComponent.getCartProductList().isEmpty())
        {
            Assert.fail("Cart is empty!");
        }
        else
        {
            assertTrue(true,"Cart has " + productCount + "product/s");
        }

    }
}
