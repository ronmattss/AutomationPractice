package org.automationtest.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {

    private WebDriver browserDriver;

    private WebElement searchBox;
    private WebElement searchButton;
    private List<WebElement> searchResults;


    public ProductPage (WebDriver driver)
    {

        browserDriver = driver;
        searchBox = browserDriver.findElement( By.xpath("//input[@id='search_product']"));
        searchButton = browserDriver.findElement( By.xpath("//button[@id='submit_search']"));
        setSearchResults(By.xpath("//div[@class='productinfo text-center']//a[@data-product-id]"));
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
    public WebElement getTextSiblingOfResultElement(int i)
    {
       return getSearchResults().get(i).findElement(By.xpath("//preceding-sibling::p"));
    }


    public void addProductToCart(WebElement product)
    {
        product.click();
    }





}
