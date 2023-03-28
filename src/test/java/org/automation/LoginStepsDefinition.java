package org.automation;

import io.cucumber.java.After;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.WebNavigator.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

public class LoginStepsDefinition  {

    WebDriver browserDriver = new FirefoxDriver();
    LoginPage loginPage;
    WebElement userElement;
    WebElement passwordElement;
    WebElement submitElement;


    boolean incompleteEmail = false;



    @Given("the user is on the Login Page")
    public void user_is_on_login_page()
    {
        browserDriver.get("https://www.automationexercise.com"); // this will go to the homepage
        browserDriver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a")).click();

        loginPage = new LoginPage(browserDriver,By.xpath("//input[@type='email']"),By.xpath("//input[@type='password']"),By.xpath("//button[text()='Login']"));

    }
    @When("User will login to the website using {string} username and {string} password")
    public void user_will_login_to_the_website_using_username_and_password(String username, String password)
    {
        loginPage.fillInUsername(username);
        loginPage.filInPassword(password);
        loginPage.clickLogin();

    }
    @Then("User will know if they are login if they can see the product page and have a result of {string}")
    public void user_will_know_if_they_are_login_if_they_can_see_the_product_page_and_have_a_result_of(String expectedAnswer)
    {
        assertEquals(expectedAnswer,String.valueOf(loginPage.verifyLogin()));
    }
    @After()
    public void closeBrowser() {
        browserDriver.quit();
    }


    public static class SearchStepsDefinition {
    }
}
