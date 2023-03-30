package org.automationtest.WebNavigator;

import org.automation.WebNavigator.CartProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class CartComponent {

    WebDriver browserDriver = new FirefoxDriver();
    // //p[@class ='text-center']//b[contains(text(),'Cart is empty!')]
    WebElement cartChecker;
    List<org.automation.WebNavigator.CartProduct> cartContent;

    public CartComponent(WebDriver driver)
    {
        this.browserDriver = driver;
        System.out.println(checkIfCartIsEmpty());
        if(!checkIfCartIsEmpty())
        {
            // get cartContent
            cartContent = new ArrayList<>();
            List<WebElement> listOfProducts = browserDriver.findElements(By.xpath("//tr[starts-with(@id,'product-')]"));
            for(int i =0; i<listOfProducts.size();i++)
            {
                cartContent.add(new CartProduct(browserDriver,listOfProducts.get(i)));
            }
        }
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
