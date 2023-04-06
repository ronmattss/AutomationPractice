package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckoutPage {

    @FindBy(xpath = "//a[@href='/payment' and contains(@class, 'check_out') and text()='Place Order']")
    WebElement placeOrderButtonElement;

    public CheckoutPage()
    {
        PageFactory.initElements(WebNavigatorHelper.getInstance().getBrowserDriver(), this);
    }

    public void clickCheckOutButton()
    {
        WebNavigatorHelper.getInstance().waitButton(placeOrderButtonElement,500);
    }
}
