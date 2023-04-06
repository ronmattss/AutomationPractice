package org.automationtest.WebNavigator;

import org.automationtest.WebNavigator.utils.WebNavigatorHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoicePage {
    @FindBy(xpath = "//a[@class='btn btn-default check_out' and contains(@href, 'download_invoice')]")
    private WebElement downloadInvoiceButton;
    @FindBy(xpath = "//a[@data-qa='continue-button' and @class='btn btn-primary']")
    private WebElement continueButton;

    public InvoicePage() {
        PageFactory.initElements(WebNavigatorHelper.getInstance().getBrowserDriver(), this);
    }

    public void clickDownloadInvoice() {
        WebNavigatorHelper.getInstance().waitButton(downloadInvoiceButton, 500);

    }

    public void clickContinue() {

        WebNavigatorHelper.getInstance().waitButton(continueButton, 500);

    }
}
