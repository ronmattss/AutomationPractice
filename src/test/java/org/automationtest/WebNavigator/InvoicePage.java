package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InvoicePage {
    private WebElement downloadInvoiceButton;
    private WebElement continueButton;

    public InvoicePage() {
        downloadInvoiceButton = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@class='btn btn-default check_out' and contains(@href, 'download_invoice')]"));
        continueButton = WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@data-qa='continue-button' and @class='btn btn-primary']"));
    }

    public void clickDownloadInvoice() {
        downloadInvoiceButton.click();
    }

    public void clickContinue() {
        continueButton.click();
    }
}
