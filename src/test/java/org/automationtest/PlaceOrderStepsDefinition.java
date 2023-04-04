package org.automationtest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.WebNavigator.*;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceOrderStepsDefinition {
    CartPage cartView;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;
    InvoicePage invoicePage;





    /**
     * Navigates to the cart view and checkout page
     *
     */
    @Given("I am ready to checkout orders")
    public void userIsReadyToCheckout() {
        ProductPage.clickCartView();

        cartView = new CartPage();
        if(cartView.checkIfCartIsEmpty()) {
            fail("The cart is empty");
        }
        cartView.clickProceedToCheckout();

        checkoutPage = new CheckoutPage();
    }

    /**
     * clicks the checkout button
     *
     */
    @When("I proceed to checkout and place my order")
    public void userPlaceOrder() {
        checkoutPage.clickCheckOutButton();
    }

    /**
     * enters card details
     * Note that there are no validations in the test website,
     */
    @And("I enter my payment details")
    public void userEntersPaymentDetails() {
        paymentPage = new PaymentPage();
        // initial fill up form to check if everything works
        paymentPage.fillPaymentForm("John Doe", "1234567890123456", "123", "12", "2025");
    }

    /**
     * confirm order and
     * Download invoice
     */
    @Then("I Confirm my order and download my invoice")
    public void userPlacedOrder() {
        paymentPage.clickPayButton();
        WebNavigatorHelper.getInstance().pauseExecution(500);
        invoicePage = new InvoicePage();
        invoicePage.clickDownloadInvoice();
        invoicePage.clickContinue();
    }





}
