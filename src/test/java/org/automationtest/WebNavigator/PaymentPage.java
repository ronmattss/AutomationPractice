package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {

    WebElement nameOfCardField;
    WebElement cardNumberField;
    WebElement cvcField;
    WebElement expirationMonthField;
    WebElement expiryYearField;
    WebElement payButton;

    /**
     * Instantiate Payment page and it's components
     */
    public PaymentPage() {

        nameOfCardField = WebNavigatorHelper.getInstance().getBrowserDriver()
.findElement(By.xpath("//input[@name='name_on_card']"));
        cardNumberField = WebNavigatorHelper.getInstance().getBrowserDriver()
.findElement(By.xpath("//input[@name='card_number']"));
        cvcField = WebNavigatorHelper.getInstance().getBrowserDriver()
.findElement(By.xpath("//input[@name='cvc']"));
        expirationMonthField = WebNavigatorHelper.getInstance().getBrowserDriver()
.findElement(By.xpath("//input[@name='expiry_month']"));
        expiryYearField = WebNavigatorHelper.getInstance().getBrowserDriver()
.findElement(By.xpath("//input[@name='expiry_year']"));
        payButton = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//button[@id='submit']"));

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
        payButton.click();
    }
    public void fillPaymentForm(String nameOnCard, String cardNumber, String cvc, String expirationMonth, String expirationYear) {
        // Fill in the form fields
        enterNameOnCard(nameOnCard);
        enterCardNumber(cardNumber);
        enterCVC(cvc);
        enterExpirationMonth(expirationMonth);
        enterExpirationYear(expirationYear);
        // Click the Pay button

    }


}
