package org.automation.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartComponent {

    WebDriver browserDriver = new FirefoxDriver();
    // //p[@class ='text-center']//b[contains(text(),'Cart is empty!')]
    WebElement cartChecker;
    List<CartProduct> cartContent;
    WebElement proceedToCheckOutButtonElement;

    public CartComponent(WebDriver driver)
    {
        this.browserDriver = driver;
        System.out.println(checkIfCartIsEmpty());
        if(!checkIfCartIsEmpty())
        {
            // get cartContent
            proceedToCheckOutButtonElement = browserDriver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]"));
            cartContent = new ArrayList<>();
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
    public void RemoveCardProduct(int cartProductIndex)
    {
        CartProduct productCartButton = cartContent.get(cartProductIndex);
        cartContent.remove(productCartButton);
        productCartButton.deleteCartProduct();


        // Thread.sleep(500);

    }
    public void clickProceedToCheckout()
    {
        proceedToCheckOutButtonElement.click();
    }


    public boolean checkIfCartIsEmpty()
    {
        try
        {
            cartChecker = browserDriver.findElement(By.xpath("//p[@class ='text-center']//b[contains(text(),'Cart is empty!')] "));
        }
        catch (Exception e)
        {
            return  true;
        }
        return false;
    }
}
