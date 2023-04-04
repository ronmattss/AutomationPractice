package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage {

    private WebElement searchBox;
    private WebElement searchButton;
    private List<WebElement> searchResults;

    /***
     * Constructor for Product Page
     */
    public ProductPage () {
        searchBox = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//input[@id='search_product']"));
        searchButton = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//button[@id='submit_search']"));
        setSearchResults(By.xpath("//div[@class='productinfo text-center']//a[@data-product-id]"));
    }

    public void searchProduct(String productName) {
        searchBox.sendKeys(productName);
        searchButton.click();
    }

    // find the results of the search
    public void setSearchResults(By resultElements) {
        searchResults = WebNavigatorHelper.getInstance().getBrowserDriver().findElements(resultElements);
        System.out.println(getSearchResults().size());
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }


    public static void clickCartView() {
        WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/view_cart']")).click();
    }

    public static void gotoProductsPage() {
        WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/products']")).click();
    }

    public void addProductToCart(WebElement product) {
        product.click();
    }

}