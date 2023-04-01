package org.automationtest.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartProduct {


    WebElement productIDElement;

    WebElement cartDeleteElement;


    public CartProduct(WebElement productElement)
    {
        productIDElement = productElement;
        cartDeleteElement = productIDElement.findElement(By.xpath("//td[@class='cart_delete']"));
    }

    public void deleteCartProduct()
    {
        cartDeleteElement.click();
    }
}
