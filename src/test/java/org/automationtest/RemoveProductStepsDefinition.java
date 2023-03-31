package org.automationtest;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.WebNavigator.CartComponent;
import org.automationtest.WebNavigator.CartModalComponent;
import org.automationtest.WebNavigator.HomePage;
import org.automationtest.WebNavigator.ProductPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.junit.After;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveProductStepsDefinition {
    WebDriver browserDriver = WebNavigatorHelper.getInstance().getBrowserDriver();
CartComponent cartView;
    LoginStepsDefinition loginStepsDefinition = new LoginStepsDefinition();
    SearchStepsDefinition searchStepsDefinition = new SearchStepsDefinition();
    AddProductStepDefinition addProductStepDefinition = new AddProductStepDefinition();
    ProductPage productPage;
    CartModalComponent cartModalComponent;
    Random random = new Random();

    @Given("I am logged in with {string} and {string}")
    public void userChecksProductFromCart(String username, String password)
    {
        loginStepsDefinition.userIsInLoginPage();
        loginStepsDefinition.userIsLoggingIn(username,password);
        loginStepsDefinition.userIsLoggedIn();
        searchStepsDefinition.userNavigatesToTheProductsPage();
        WebNavigatorHelper.getInstance().pauseExecution(500);
    }
    @When("I View my cart I have {int} products")
    public void userRemovesAProductFromCart(int productCount)
    {
        HomePage homePage = new HomePage(browserDriver);
        homePage.clickCartView();
        WebNavigatorHelper.getInstance().pauseExecution(1000);
         cartView = new CartComponent(browserDriver);
        System.out.println("size: "+cartView.getCartProductList().size());
          if( cartView.getCartProductList().size()<productCount)
         {

             addProductStepDefinition.userIsInProductsPageAndSearchedAProduct("tshirt");
             addProductStepDefinition.userAddProductsToTheCart( productCount - cartView.getCartProductList().size());

             homePage.clickCartView();
             assertEquals(productCount,cartView.getCartProductList().size());
         }
          else if(cartView.getCartProductList().size()>productCount)
          {
              WebNavigatorHelper.getInstance().pauseExecution(1000);
              int tempSize = cartView.getCartSize() -productCount;
              for(int i =0; i<tempSize;i++)
              {
                  cartView.RemoveCardProduct(random.nextInt(cartView.getCartProductList().size()));
              }
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
        if(cartView.getCartProductList().isEmpty())
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
            for(int i =0; i<tempSize;i++)
            {
                cartView.RemoveCardProduct(random.nextInt(cartView.getCartProductList().size()));
            }
        }
        cartView = new CartComponent(browserDriver);
        assertEquals(productCount,cartView.getCartProductList().size());
    }


}
