package org.automationtest.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver browserDriver;
    WebElement cartView;









    public HomePage(WebDriver driver)
    {
        browserDriver = driver;
        cartView =  browserDriver.findElement(By.xpath("//a[@href='/view_cart' and contains(text(),'Cart')]"));
    }
    public void clickCartView()
    {
         ((JavascriptExecutor) browserDriver).executeScript("arguments[0].scrollIntoView(true);", cartView);
        cartView.click();
    }



}
