package org.automation;


import org.automation.WebNavigator.*;
import org.automation.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");


        WebDriver browserDriver = new FirefoxDriver();
        LoginPage loginPage;
        ProductPage productPage;
        browserDriver.manage().window().maximize();
        browserDriver.get("https://www.automationexercise.com"); // this will go to the homepage
        browserDriver.findElement(By.xpath("//a[@href='/login']")).click();
        // ~~ Login Page
        loginPage = new LoginPage(browserDriver,By.xpath("//input[@type='email']"),
                By.xpath("//input[@type='password']"),By.xpath("//button[text()='Login']"));

        loginPage.fillInUsername("ronmattss@gmail.com");
        loginPage.filInPassword("@Ron192000");
        loginPage.clickLogin();

        // ~~Products Search~~
        browserDriver.findElement(By.xpath("//a[@href='/products']")).click();

        // Why is there an ad?
        System.out.println(browserDriver.switchTo().defaultContent().getTitle());
        // try and remove ads if present
        try{
            WebNavigatorHelper.getInstance().dismissAd(browserDriver);
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("No ads Detected");
        }
        productPage = new ProductPage(browserDriver,By.xpath("//input[@id='search_product']"),
                By.xpath("//button[@id='submit_search']"));

        // Search a Product
        productPage.searchProduct("tshirt");

        // Results

        productPage.setSearchResults(By.xpath("//div[@class='productinfo text-center']//a[@data-product-id]"));

        for(int i = 0;i<productPage.getSearchResults().size();i++)
        {
            String product = productPage.getSearchResults().get(i).findElement(By.xpath("//preceding-sibling::p")).getText().toLowerCase();

            if(product.contains("t-shirt") || product.contains("tshirt")){
                System.out.println(true);
            }
        }

        // Add to cart
        Random random = new Random();
        CartModalComponent cartModal = new CartModalComponent(browserDriver);

        List<WebElement> products = productPage.getSearchResults();

        for(int i = 0; i<2; i++)
        {
            int getRandomIndex = random.nextInt(products.size());
            WebElement productCartButton = products.get(getRandomIndex);

            WebElement element = productCartButton;
            ((JavascriptExecutor) browserDriver).executeScript("arguments[0].scrollIntoView(true);", element);

           // Thread.sleep(500);
            productPage.addProductToCart(productCartButton);
            products.remove(productCartButton);
            try
            {
                Thread.sleep(500);
            }
            catch (Exception e)
            {
                System.out.println("pausing execution");
            }
            cartModal.clickContinueShoppingButton();
        }
            // View Cart
            cartModal.viewCart(); // I think I need to remove this and change to the default href

            CartComponent cartView = new CartComponent(browserDriver);

            try
            {
                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                System.out.println("pushing execution");
            }
            // Remove one Random Product
        if(!cartView.getCartProductList().isEmpty())
        {
            cartView.RemoveCardProduct(random.nextInt(cartView.getCartProductList().size()));

        }
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("pausing execution");
        }

        // I can proceed to checkout page and place my order.

        cartView.clickProceedToCheckout();
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("pausing execution");
        }
        CheckoutPage checkoutPage = new CheckoutPage(browserDriver);
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("pausing execution");
        }

        checkoutPage.clickCheckOutButton();
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("pausing execution");
        }
        // Pay Order

        PaymentPage paymentPage = new PaymentPage(browserDriver);
        paymentPage.fillAndSubmitPaymentForm("John Doe", "1234567890123456", "123", "12", "2025");

        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("pausing execution");
        }

        InvoicePage invoicePage = new InvoicePage(browserDriver);

        invoicePage.clickDownloadInvoice();
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("pausing execution");
        }
        invoicePage.clickContinue();


    }


}

