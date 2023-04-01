package org.automationtest;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    public void userChecksProductFromCart(String username, String password)
    {
        homePage.clickLoginView();
        loginPage = new LoginPage();
        loginPage.Login(username,password);


        searchStepsDefinition.userNavigatesToTheProductsPage();
        WebNavigatorHelper.getInstance().pauseExecution(500);
    }
    @When("I View my cart I have {int} products")
    public void userRemovesAProductFromCart(int productCount)
    {
        homePage.clickCartView();

        WebNavigatorHelper.getInstance().pauseExecution(1000);
         cartView = new CartComponent();

        System.out.println("size: "+cartView.getCartProductList().size());

        if(cartView.getCartProductList().size()> productCount)
        {
            WebNavigatorHelper.getInstance().pauseExecution(1000);
            int tempSize = cartView.getCartSize() -productCount;
            for(int i =0; i<tempSize;i++)
            {
                cartView.RemoveCardProduct(random.nextInt(cartView.getCartProductList().size()));
            }
        }

        else if(cartView.getCartProductList().size()< productCount)
        {
            addProductStepDefinition.userIsInProductsPageAndSearchedAProduct("tshirt");
            addProductStepDefinition.userAddProductsToTheCart( productCount - cartView.getCartProductList().size());
            homePage.clickCartView();

            cartView = new CartComponent();
            WebNavigatorHelper.getInstance().pauseExecution(500);
            assertEquals(productCount,cartView.getCartProductList().size());
        }
         else
         {
             assertEquals(productCount,cartView.getCartProductList().size());
             WebNavigatorHelper.getInstance().pauseExecution(500);
         }
    }
    @Then("I should have {int} product in it")
    public void userSeeAProductInTheCart(int productCount)
    {

        if(cartView.getCartProductList().size() == 1)
        {
            assertEquals(productCount,cartView.getCartProductList().size());
            return;
        }
        else if(cartView.getCartProductList().isEmpty() || cartView.getCartProductList().size() == 0)
        {
            searchStepsDefinition.userNavigatesToTheProductsPage();
            searchStepsDefinition.userSearchesForProducts("tshirts");
            searchStepsDefinition.userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage();

            addProductStepDefinition.userIsInProductsPageAndSearchedAProduct("tshirts");
            addProductStepDefinition.userAddProductsToTheCart(1);
            addProductStepDefinition.userVerifiesCartProducts(1);
        }
        else
        {
            int tempSize = cartView.getCartSize() -productCount;
            if(tempSize == 0)
            {
                assertEquals(productCount,cartView.getCartProductList().size());
            }
            else
            {
                for(int i =0; i<tempSize;i++)
                {
                    cartView.RemoveCardProduct(random.nextInt(cartView.getCartProductList().size()));
                }
            }

        }
        System.out.println(cartView.getCartProductList().size());
        assertEquals(productCount,cartView.getCartProductList().size());
    }


}
