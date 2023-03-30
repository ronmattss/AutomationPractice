package org.automationtest.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartModalComponent {
    WebDriver browserDriver;
    private WebElement cartModalContinueButton;
    private WebElement cartModalViewCartAnchor;
    private WebElement cartModal;

    public CartModalComponent(WebDriver driver)
    {
        browserDriver = driver;
        cartModal = browserDriver.findElement(By.xpath("//*[@id='cartModal']"));
        cartModalContinueButton = browserDriver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block' and text()='Continue Shopping']"));
        cartModalViewCartAnchor = browserDriver.findElement(By.xpath("//*[@id='cartModal']//a[@href='/view_cart']"));

    }

    public boolean checkIfModalIsShown()
    {
        return cartModal.isDisplayed();
    }
    public void viewCart()
    {
        cartModalViewCartAnchor.click();
    }
    public void clickContinueShoppingButton()
    {
    cartModalContinueButton.click();
    }
}
