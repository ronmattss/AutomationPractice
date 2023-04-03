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
    WebDriver browserDriver = WebNavigatorHelper.getInstance().getBrowserDriver();
    CartComponent cartView;

    SearchStepsDefinition searchStepsDefinition = new SearchStepsDefinition();
    AddProductStepDefinition addProductStepDefinition = new AddProductStepDefinition();
    ProductPage productPage;
    LoginPage loginPage;
    CartModalComponent cartModalComponent;
    HomePage homePage = new HomePage();
    Random random = new Random();


    @Given("I am logged in with {string} and {string}")
    public void userChecksProductFromCart(String username, String password) {
        homePage.clickLoginView();
        loginPage = new LoginPage();
        loginPage.Login(username, password);


        searchStepsDefinition.userNavigatesToTheProductsPage();
        WebNavigatorHelper.getInstance().pauseExecution(500);
    }

    @When("I have at least {int} products in my cart")
    public void userVerifiesCartProduct(int productCount) {
        homePage.clickCartView();
        WebNavigatorHelper.getInstance().pauseExecution(1000);
        cartView = new CartComponent();
        if (cartView.getCartProductList().isEmpty()) {
            CustomLogger.logInfo("There are no products in the cart");
            fail("There are no products in the cart");
        } else if (cartView.getCartProductList().size() > productCount) {
            CustomLogger.logInfo("You have " + cartView.getCartProductList().size() + " product/s");
            fail("Expected " + productCount + " products, but found " + cartView.getCartProductList().size());
        }
        else
            CustomLogger.logInfo("You have " + cartView.getCartProductList().size() + " product/s");
            assertTrue(true);
    }

    @Then("I remove a product and I should have {int} products left")
    public void userRemovesAProduct(int productCount) {
        // if cart is empty don't remove anything
        if (cartView.getCartProductList().isEmpty()) {
            CustomLogger.logInfo("Cart is empty");
            fail("Cart is empty");
        } else if (cartView.getCartProductList().size() == productCount + 1) {
            cartView.RemoveCardProduct(random.nextInt(cartView.getCartProductList().size()));
            assertEquals(productCount, cartView.getCartProductList().size(), "Expected " + productCount + " products, but found " + cartView.getCartProductList().size());
        } else {
            assertEquals(productCount, cartView.getCartProductList().size());
        }
    }

    }


