package org.automationtest.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
    WebDriver browserDriver;
    WebElement nameOfCardField;
    WebElement cardNumberField;
    WebElement cvcField;
    WebElement expirationMonthField;
    WebElement expiryYearField;
    WebElement payButton;

    public PaymentPage(WebDriver driver) {
        browserDriver = driver;
        nameOfCardField = browserDriver.findElement(By.xpath("//input[@name='name_on_card']"));
        cardNumberField = browserDriver.findElement(By.xpath("//input[@name='card_number']"));
        cvcField = browserDriver.findElement(By.xpath("//input[@name='cvc']"));
        expirationMonthField = browserDriver.findElement(By.xpath("//input[@name='expiry_month']"));
        expiryYearField = browserDriver.findElement(By.xpath("//input[@name='expiry_year']"));
        payButton = driver.findElement(By.xpath("//button[@id='submit']"));
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
        nameOfCardField.sendKeys(nameOnCard);
        cardNumberField.sendKeys(cardNumber);
        cvcField.sendKeys(cvc);
        expirationMonthField.sendKeys(expirationMonth);
        expiryYearField.sendKeys(expirationYear);

        // Click the Pay button

    }


}
