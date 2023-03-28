package org.automation.WebNavigator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {

    private WebDriver browserDriver;

    private WebElement searchBox;
    private WebElement searchButton;
    public ProductPage (WebDriver driver)
    {
        browserDriver = driver;
    }


}
