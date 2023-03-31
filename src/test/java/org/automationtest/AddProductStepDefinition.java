package org.automationtest;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import org.automationtest.WebNavigator.CartComponent;
import org.automationtest.WebNavigator.CartModalComponent;
import org.automationtest.WebNavigator.ProductPage;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddProductStepDefinition {

    WebDriver browserDriver = WebNavigatorHelper.getInstance().getBrowserDriver();
    CartModalComponent cartModal;
    CartComponent cartView;
    ProductPage productPage;
    Random random = new Random();
    LoginStepsDefinition loginStepsDefinition = new LoginStepsDefinition();
    SearchStepsDefinition searchStepsDefinition = new SearchStepsDefinition();



    @Given("I am logged in with my credentials {string} and {string}")
    public void userVerifiesLogin(String username,String password)
    {
        loginStepsDefinition.userIsInLoginPage();
        loginStepsDefinition.userIsLoggingIn(username,password);
        loginStepsDefinition.userIsLoggedIn();
    }
    @Given("I searched {string}")
    public void userIsInProductsPageAndSearchedAProduct(String product)
    {
        searchStepsDefinition.userNavigatesToTheProductsPage();
        WebNavigatorHelper.getInstance().pauseExecution(500);
        searchStepsDefinition.userSearchesForProducts(product);
        searchStepsDefinition.userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage();
    }
    @When("I add {int} products to my cart")
    public void userAddProductsToTheCart(int numOfProducts)
    {
        productPage = new ProductPage(browserDriver);
        cartModal = new CartModalComponent(browserDriver);
        List<WebElement> products = productPage.getSearchResults();
        System.out.println(+products.size());
        for(int i = 0; i<numOfProducts; i++)
        {
            int getRandomIndex = random.nextInt(products.size());
            WebElement productCartButton = products.get(getRandomIndex);

            WebElement element = productCartButton;
            ((JavascriptExecutor) browserDriver).executeScript("arguments[0].scrollIntoView(true);", element);

            productPage.addProductToCart(productCartButton);
            products.remove(productCartButton);
            WebNavigatorHelper.getInstance().pauseExecution(500);
            cartModal.clickContinueShoppingButton();
        }
    }
    @Then("I should see {int} t-shirts in my cart")
    public void userVerifiesCartProducts(int productsInCart)
    {
        WebNavigatorHelper.getInstance().pauseExecution(500);
        cartModal.viewCart();

        cartView = new CartComponent(browserDriver);
        WebNavigatorHelper.getInstance().pauseExecution(1000);
       // assertEquals(productsInCart,cartView.getCartSize());
    }


}
