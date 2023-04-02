package org.automationtest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.automationtest.WebNavigator.*;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseStepsDefinition {

    WebDriver browserDriver = WebNavigatorHelper.getInstance().getBrowserDriver();
    HomePage homepage = new HomePage();
    LoginPage loginPage;
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
        homepage.clickLoginView();
        loginPage = new LoginPage();
        loginPage.Login(username,password);
    }
    @And("I am ready to checkout orders")
    public void userIsReadyToCheckout()
    {
                homepage.clickCartView();

        cartView = new CartComponent();
        if(cartView.checkIfCartIsEmpty())
        {
            searchStepsDefinition.userNavigatesToTheProductsPage();
            searchStepsDefinition.userSearchesForProducts("tshirts");
            searchStepsDefinition.userShouldBeAbleToSeeTheProductsOnTheSearchResultsPage();

            addProductStepDefinition.userIsInProductsPageAndSearchedAProduct("tshirts");
            addProductStepDefinition.userAddProductsToTheCart(1);
            addProductStepDefinition.userVerifiesCartProducts(1);
            cartView = new CartComponent();
        }
        cartView.clickProceedToCheckout();

        checkoutPage = new CheckoutPage();
    }

        @When("I proceed to checkout and place my order")
        public void userPlaceOrder()
        {
            checkoutPage.clickCheckOutButton();
        }
        @And("I enter my card details")
        public void userEnterDetails()
        {
            paymentPage = new PaymentPage();
            paymentPage.fillPaymentForm("John Doe", "1234567890123456", "123", "12", "2025");

        }
        @Given("I Confirm my order")
        public void userPlacedOrder()
        {
            paymentPage.clickPayButton();
            WebNavigatorHelper.getInstance().pauseExecution(500);
            invoicePage = new InvoicePage();
            invoicePage.clickDownloadInvoice();

        }
        @When("I should be able to download my invoice")
    public void userCanSeeInvoice()
    {
        invoicePage.clickContinue();
    }




}
