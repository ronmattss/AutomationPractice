package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {


    WebElement cartView;
    WebElement loginView;


    public HomePage() {
        cartView = WebNavigatorHelper.getInstance().getBrowserDriver()
                .findElement(By.xpath("//a[@href='/view_cart' and contains(text(),'Cart')]"));
        loginView = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/login']"));

    }
    public void GoToHomepage()
    {
        WebNavigatorHelper.getInstance().getBrowserDriver().get("https://www.automationexercise.com"); // this will go to the homepage
    }
    public void MaximizeWindow()
    {
        WebNavigatorHelper.getInstance().getBrowserDriver().manage().window().maximize();

    }


    public void clickCartView() {
        cartView = WebNavigatorHelper.getInstance().getBrowserDriver()
                .findElement(By.xpath("//a[@href='/view_cart' and contains(text(),'Cart')]"));
        // For some browser reason, sometimes this is not needed
        // ((JavascriptExecutor) WebNavigatorHelper.getInstance().getBrowserDriver()
        // ).executeScript("arguments[0].scrollIntoView(true);", cartView);
        cartView.click();
    }

    public void clickLoginView() {
        loginView = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/login']"));

     //   ((JavascriptExecutor) WebNavigatorHelper.getInstance().getBrowserDriver()
     //   ).executeScript("arguments[0].scrollIntoView(true);", loginView);
        loginView.click();
    }


}
