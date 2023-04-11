package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

    @FindBy(name = "name_on_card")
    private WebElement nameOfCardField;

    @FindBy(name = "card_number")
    private WebElement cardNumberField;

    @FindBy(name = "cvc")
    private WebElement cvcField;

    @FindBy(name = "expiry_month")
    private WebElement expirationMonthField;

    @FindBy(name = "expiry_year")
    private WebElement expiryYearField;

    @FindBy(id = "submit")
    private WebElement payButton;


    /**
     * Instantiate Payment page and it's components
     */
    public PaymentPage() {

        PageFactory.initElements(WebNavigatorHelper.getInstance().getBrowserDriver(), this);
    }

    public void enterNameOnCard(String name) {
        nameOfCardField.sendKeys(name);
    }

    public void enterCardNumber(String number) {
        cardNumberField.sendKeys(number);
    }

    public void enterCVC(String cvc) {
        cvcField.sendKeys(cvc);
    }

    public void enterExpirationMonth(String month) {
        expirationMonthField.sendKeys(month);
    }

    public void enterExpirationYear(String year) {
        expiryYearField.sendKeys(year);
    }
    public void clickPayButton() {
        WebNavigatorHelper.getInstance().waitButton(payButton, 500);

    }
    public void fillPaymentForm(String nameOnCard, String cardNumber, String cvc, String expirationMonth, String expirationYear) {
        // Fill in the form fields
        enterNameOnCard(nameOnCard);
        enterCardNumber(cardNumber);
        enterCVC(cvc);
        enterExpirationMonth(expirationMonth);
        enterExpirationYear(expirationYear);

    }


}
