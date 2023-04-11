package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductPage {

    @FindBy(id = "search_product")
    private WebElement searchBox;
    @FindBy(id = "submit_search")
    private WebElement searchButton;


    @FindBy(className = "btn-success")
    private WebElement cartModalContinueButton;
    @FindBy(xpath = "//a[@href='/view_cart']")
    private WebElement cartModalViewCartAnchor;
    @FindBy(id = "cartModal")
    private WebElement cartModal;
    @FindBy(xpath = "//div[@class='productinfo text-center']//a[@data-product-id]")
    private List<WebElement> searchResults;



    public ProductPage () {
        PageFactory.initElements(WebNavigatorHelper.getInstance().getBrowserDriver(), this);
    }
    public void searchProduct(String productName) {
        searchBox.sendKeys(productName);
        searchButton.click();
    }
    public List<WebElement> getSearchResults() {
        return searchResults;
    }
    // for some reason

    public void addProductToCart(WebElement product) {

        WebNavigatorHelper.getInstance().waitButton(product,500);
    }
    public void clickContinueShoppingButton()
    {
        WebNavigatorHelper.getInstance().waitButton(cartModalContinueButton,500);
    }

}