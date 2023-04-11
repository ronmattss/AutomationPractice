package org.automationtest.WebNavigator;

import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage {


    @FindBy(xpath = "//tr[starts-with(@id,'product-')]")
    List<WebElement> listOfProducts;
    @FindBy(id = "empty_cart")
    private WebElement cartChecker;
    @FindBy(xpath = "//a[contains(text(),'Proceed To Checkout')]")
    private WebElement proceedToCheckOutButtonElement;
    @FindBy(xpath = "//tr[starts-with(@id,'product-')]")
    private final List<CartProduct> cartContent;


    /**
     * Initializes Cart page
     * Gets the list of products if available
     */
    public CartPage() {
        cartContent = new ArrayList<>();
        PageFactory.initElements(WebNavigatorHelper.getInstance().getBrowserDriver(), this);
        if (!checkIfCartIsEmpty()) {
            for (int i = 0; i < listOfProducts.size(); i++) {
                cartContent.add(new CartProduct(listOfProducts.get(i)));
            }
        }
    }

    public List<CartProduct> getCartProductList() {
        return cartContent;
    }

    public int getCartSize() {
        if (cartContent == null) {
            return 0;
        }
        return cartContent.size();
    }

    public void RemoveCardProduct(int cartProductIndex) {
        CartProduct productCartButton = cartContent.get(cartProductIndex);
        CustomLogger.logInfo("Removing product: " + cartProductIndex);
        cartContent.remove(productCartButton);
        productCartButton.deleteCartProduct();
    }

    public void clickProceedToCheckout() {
        WebNavigatorHelper.getInstance().waitButton(proceedToCheckOutButtonElement, 500);
    }


    public boolean checkIfCartIsEmpty() {
        return cartChecker.isDisplayed();
    }
}
