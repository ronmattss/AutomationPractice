package org.automationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.*;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveProductStepsDefinition {

    CartPage cartView;
    Random random = new Random();
    /**
    *
    * Step that navigates to the cart
    *
    * */

    @Given("I view my cart")
    public void userChecksProductFromCart() {
        ProductPage.clickCartView();
        WebNavigatorHelper.getInstance().pauseExecution(500);
    }

    /**
     * Step that verifies the count of products
     * @param productCount is the number of products expected
     * */
    @When("I have {int} products in my cart")
    public void userVerifiesCartProduct(int productCount) {
        WebNavigatorHelper.getInstance().pauseExecution(1000);
        cartView = new CartPage();
        if (cartView.getCartProductList().isEmpty()) {
            CustomLogger.logInfo("There are no products in the cart");
            assertTrue(cartView.getCartProductList().isEmpty(),"There are no products in the cart");
        } else if (cartView.getCartProductList().size() == productCount) {
            CustomLogger.logInfo("You have " + cartView.getCartProductList().size() + " product/s");
            assertTrue(true,"You have "+ productCount+ " in your cart");
        }
        else
            CustomLogger.logInfo("You have " + cartView.getCartProductList().size() + " product/s");
            assertFalse(false,"Expected " + productCount + " products, but found " + cartView.getCartProductList().size());
    }


    @Then("I remove a product and I should have {int} products left")
    public void userRemovesAProduct(int productCount) {
        // if cart is empty don't remove anything
        if (cartView.getCartProductList().isEmpty()) {
            CustomLogger.logInfo("There are no products in the cart");
            assertTrue(cartView.getCartProductList().isEmpty(),"There are no products in the cart");
        } else if (cartView.getCartProductList().size() == productCount + 1) {
            cartView.RemoveCardProduct(random.nextInt(cartView.getCartProductList().size()));
            assertEquals(productCount, cartView.getCartProductList().size());
            CustomLogger.logInfo("Removed one product");
        } else {
            CustomLogger.logWarning("Cannot remove anymore product, cart will be empty");
            assertFalse(false,"Cannot remove anymore product, cart will be empty ");
        }
    }

    }


