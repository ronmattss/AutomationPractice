package org.automation.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {

    private WebDriver browserDriver;

    private WebElement searchBox;
    private WebElement searchButton;
    private List<WebElement> searchResults;

    public ProductPage (WebDriver driver, By searchBoxElement,By searchButtonElement)
    {
        browserDriver = driver;
        searchBox = browserDriver.findElement(searchBoxElement);
        searchButton = browserDriver.findElement(searchButtonElement);
    }



    public void searchProduct(String productName)
    {
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    // find the results of the search
    public void setSearchResults(By resultElements)
    {

        searchResults = browserDriver.findElements(resultElements);
        System.out.println(getSearchResults().size());

    }

    public List<WebElement> getSearchResults()
    {
    return searchResults;
    }

    public void addProductToCart(WebElement product)
    {
        product.click();
    }





}
