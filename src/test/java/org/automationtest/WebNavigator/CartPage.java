package org.automationtest.WebNavigator;

import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {


    private WebDriver driver;
    private WebElement cartChecker;
    private WebElement proceedToCheckOutButtonElement;
    private List<CartProduct> cartContent;


    /**
     * Initializes Cart page
     * Gets the list of products if available
     */
    public CartPage()
    {

        this.driver = WebNavigatorHelper.getInstance().getBrowserDriver();
        cartChecker = driver.findElement(By.id("empty_cart"));
        cartContent = new ArrayList<>();
        if (!checkIfCartIsEmpty()) {
            proceedToCheckOutButtonElement = driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]"));

            // get cartContent
            List<WebElement> listOfProducts = driver.findElements(By.xpath("//tr[starts-with(@id,'product-')]"));
            for (int i = 0; i < listOfProducts.size(); i++) {
                cartContent.add(new CartProduct(listOfProducts.get(i)));
            }
        }
    }

    public List<CartProduct> getCartProductList()
    {
        return cartContent;
    }
    public int getCartSize()
    {
        if (cartContent == null)
        {
            return 0;
        }
        return cartContent.size();
    }
    public void RemoveCardProduct(int cartProductIndex)
    {
        CartProduct productCartButton = cartContent.get(cartProductIndex);
        CustomLogger.logInfo("Removing product: "+ cartProductIndex);
        cartContent.remove(productCartButton);
        productCartButton.deleteCartProduct();
    }
    public void clickProceedToCheckout()
    {
        proceedToCheckOutButtonElement.click();
    }


    public boolean checkIfCartIsEmpty()
    {

        if(cartChecker == null)
        {
            return false;
        }
        else
        {
            return cartChecker.isDisplayed();
        }

    }
}
