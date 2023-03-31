package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    WebElement placeOrderButtonElement;

    public CheckoutPage()
    {
        placeOrderButtonElement = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/payment' and contains(@class, 'check_out') and text()='Place Order']"));
    }

    public void clickCheckOutButton()
    {
        ((JavascriptExecutor) WebNavigatorHelper.getInstance().getBrowserDriver()).executeScript("arguments[0].scrollIntoView(true);", placeOrderButtonElement);

        placeOrderButtonElement.click();
    }
}
