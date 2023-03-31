package org.automationtest.WebNavigator;

import org.automation.WebNavigator.CartProduct;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartComponent {

    WebDriver browserDriver;
    WebElement cartChecker;
    WebElement proceedToCheckOutButtonElement;
    List<CartProduct> cartContent;

    public CartComponent(WebDriver driver)
    {
        this.browserDriver = driver;
//        System.out.println("?"+checkIfCartIsEmpty());
//        System.out.println(cartChecker.getText());
        cartChecker = browserDriver.findElement(By.id("empty_cart"));
        cartContent = new ArrayList<>();
        if(!checkIfCartIsEmpty())
        {
            proceedToCheckOutButtonElement = browserDriver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]"));

            // get cartContent
            List<WebElement> listOfProducts = browserDriver.findElements(By.xpath("//tr[starts-with(@id,'product-')]"));
            for(int i =0; i<listOfProducts.size();i++)
            {
                cartContent.add(new CartProduct(browserDriver,listOfProducts.get(i)));
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
