package org.automation.WebNavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InvoicePage {

    private WebDriver browserDriver;
    private WebElement downloadInvoiceButton;
    private WebElement continueButton;

    public InvoicePage(WebDriver driver) {
        this.browserDriver = driver;
        downloadInvoiceButton = driver.findElement(By.xpath("//a[@class='btn btn-default check_out' and contains(@href, 'download_invoice')]"));
        continueButton = driver.findElement(By.xpath("//a[@data-qa='continue-button' and @class='btn btn-primary']"));
    }

    public void clickDownloadInvoice() {
        downloadInvoiceButton.click();
    }

    public void clickContinue() {
        continueButton.click();
    }
}
