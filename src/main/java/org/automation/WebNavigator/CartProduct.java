package org.automation.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartProduct {
    WebDriver browserDriver;

    WebElement productIDElement;

    WebElement cartDeleteElement;

    public CartProduct(WebDriver driver, WebElement productElement)
    {
        browserDriver = driver;
        productIDElement = productElement;
        cartDeleteElement = productIDElement.findElement(By.xpath("//td[@class='cart_delete']"));

    }

    public void deleteCartProduct()
    {
        cartDeleteElement.click();
    }
}
