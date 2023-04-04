package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartModalComponent {
    private WebElement cartModalContinueButton;
    private WebElement cartModalViewCartAnchor;
    private WebElement cartModal;


    /**
     * Constructor for the Cart component. Initializes the cart checker, proceeds to checkout button element,
     * and cart content.
     */
    public CartModalComponent()
    {

        cartModal = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//*[@id='cartModal']"));
        cartModalContinueButton = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block' and text()='Continue Shopping']"));
        cartModalViewCartAnchor = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/view_cart']"));

    }

    /**
     * Returns the list of cart products
     * @return the list of cart products
     */
    public boolean checkIfModalIsShown()
    {
        return cartModal.isDisplayed();
    }
    public void viewCart()
    {
      //  ((JavascriptExecutor) WebNavigatorHelper.getInstance().getBrowserDriver()).executeScript("arguments[0].scrollIntoView(true);", cartModalViewCartAnchor);
        cartModalViewCartAnchor.click();
    }
    public void clickContinueShoppingButton()
    {
    cartModalContinueButton.click();
    }
}
