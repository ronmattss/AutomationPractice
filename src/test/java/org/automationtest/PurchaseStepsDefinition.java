package org.automationtest;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.automationtest.WebNavigator.*;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.junit.After;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseStepsDefinition {

    WebDriver browserDriver = WebNavigatorHelper.getInstance().getBrowserDriver();
    HomePage homepage;
    ProductPage productPage;
    CartModalComponent cartModal;
    CartComponent cartView;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;
    InvoicePage invoicePage;
    Random random  = new Random();
    LoginStepsDefinition loginStepsDefinition = new LoginStepsDefinition();
    SearchStepsDefinition searchStepsDefinition = new SearchStepsDefinition();
    AddProductStepDefinition addProductStepDefinition = new AddProductStepDefinition();


    @Given("I logged in with my credentials {string} and {string}")
    public void userIsLoggedIn(String username, String password)
    {
        loginStepsDefinition.userIsInLoginPage();
        loginStepsDefinition.userIsLoggingIn(username,password);
        loginStepsDefinition.userIsLoggedIn();



    }
    @And("I am ready to checkout orders")
    public void userIsReadyToCheckout()
    {
        homepage = new HomePage(browserDriver);

        homepage.clickCartView();

        cartView = new CartComponent(browserDriver);
        if(cartView.checkIfCartIsEmpty())
        {
            searchStepsDefinition.userNavigatesToTheProductsPage();
            searchStepsDefinition.userSearchesForProducts("tshirts");
            searchStepsDefinition.userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage();

            addProductStepDefinition.userIsInProductsPageAndSearchedAProduct("tshirts");
            addProductStepDefinition.userAddProductsToTheCart(1);
            addProductStepDefinition.userVerifiesCartProducts(1);
            cartView = new CartComponent(browserDriver);
        }
        cartView.clickProceedToCheckout();

        checkoutPage = new CheckoutPage(browserDriver);
    }

        @When("I proceed to checkout and place my order")
        public void userPlaceOrder()
        {
            checkoutPage.clickCheckOutButton();
        }
        @And("I enter my card details")
        public void userEnterDetails()
        {
            paymentPage = new PaymentPage(browserDriver);
            paymentPage.fillPaymentForm("John Doe", "1234567890123456", "123", "12", "2025");

        }
        @Given("I Confirm my order")
        public void userPlacedOrder()
        {
            paymentPage.clickPayButton();
            WebNavigatorHelper.getInstance().pauseExecution(500);
            invoicePage = new InvoicePage(browserDriver);
            invoicePage.clickDownloadInvoice();

        }
        @When("I should be able to download my invoice")
    public void userCanSeeInvoice()
    {
        invoicePage.clickContinue();
    }



}
