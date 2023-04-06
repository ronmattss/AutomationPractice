package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {


    @FindBy(xpath = "//a[@href='/view_cart' and contains(text(),'Cart')]")
    WebElement cartView;
    @FindBy(xpath = "//a[@href='/login']")

    WebElement loginView;
    @FindBy(xpath = "//a[@href='/products']")
    WebElement productPageAnchor;


    public HomePage() {
       PageFactory.initElements(WebNavigatorHelper.getInstance().getBrowserDriver(), this);

    }

    public void goToProductPage()
    {
        productPageAnchor.click(); // this will go to the homepage
    }


    public void clickCartView() {
        WebNavigatorHelper.getInstance().waitButton(cartView, 500);

    }

    public void clickLoginView() {
        WebNavigatorHelper.getInstance().waitButton(loginView, 500);
    }


}
