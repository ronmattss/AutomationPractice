/**
 * This class defines the Cucumber step definitions for adding products to the cart.
 */

package org.automationtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.WebNavigator.CartPage;
import org.automationtest.WebNavigator.CartModalComponent;
import org.automationtest.WebNavigator.ProductPage;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddProductStepDefinition {

    CartModalComponent cartModal;
    CartPage cartView;
    ProductPage productPage;
    Random random = new Random();
    SearchStepsDefinition searchStepsDefinition = new SearchStepsDefinition();

    /**
     * This step searches for a product and navigates to the search results page.
     * @param product the name of the product to search for
     */
    @Given("I searched {string}")
    public void userSearchesForProduct (String product)
    {
        // This uses the search steps
        searchStepsDefinition.userNavigatesToTheProductsPage();
        WebNavigatorHelper.getInstance().pauseExecution(500);
        searchStepsDefinition.userSearchesForProducts(product);
        searchStepsDefinition.userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage();
    }

    /**
     * This step adds a specified number of random products to the cart from the search results page.
     * @param numberOfProducts the number of products to add to the cart
     */
    @When("I add {int} products to my cart")
    public void userAddProductsToTheCart(int numberOfProducts)
    {
        productPage = new ProductPage();
        cartModal = new CartModalComponent();

        // get the list of products from the results
        List<WebElement> products = productPage.getSearchResults();

        System.out.println(+products.size());

        //Randomly add a product
        for(int i = 0; i<numberOfProducts; i++)
        {
            int getRandomIndex = random.nextInt(products.size());
            WebElement productCartButton = products.get(getRandomIndex);

            ((JavascriptExecutor) WebNavigatorHelper.getInstance().getBrowserDriver()).executeScript("arguments[0].scrollIntoView(true);", productCartButton);

            productPage.addProductToCart(productCartButton);

            products.remove(productCartButton);
            WebNavigatorHelper.getInstance().pauseExecution(500);

            cartModal.clickContinueShoppingButton();
        }
    }

    /**
     * This step verifies that the number of products in the cart matches the specified value.
     * @param productsInCart the expected number of products in the cart
     */
    @Then("I should see {int} t-shirts in my cart")
    public void userVerifiesCartProducts(int productsInCart)
    {
        WebNavigatorHelper.getInstance().pauseExecution(500);
        cartModal.viewCart();

        cartView = new CartPage();
        WebNavigatorHelper.getInstance().pauseExecution(1000);
        assertEquals(productsInCart,cartView.getCartSize());
    }
}