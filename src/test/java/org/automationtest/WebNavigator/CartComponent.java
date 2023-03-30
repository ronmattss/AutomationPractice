package org.automationtest.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class CartComponent {

    WebDriver browserDriver = new FirefoxDriver();
    // //p[@class ='text-center']//b[contains(text(),'Cart is empty!')]
    WebElement cartChecker;
    List<WebElement> cartContent;

    public CartComponent(WebDriver driver)
    {
        this.browserDriver = driver;
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
