package org.automation.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    WebDriver browserDriver;
    WebElement placeOrderButtonElement;

    public CheckoutPage(WebDriver driver)
    {
        browserDriver = driver;
        placeOrderButtonElement = browserDriver.findElement(By.xpath("//a[@href='/payment' and contains(@class, 'check_out') and text()='Place Order']"));
    }

    public void clickCheckOutButton()
    {
        placeOrderButtonElement.click();
    }
}
