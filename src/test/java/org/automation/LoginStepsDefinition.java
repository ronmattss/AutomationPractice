package org.automation;

import io.cucumber.java.After;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

public class LoginStepsDefinition  {

    WebDriver browserDriver = new FirefoxDriver();
    WebElement userElement;
    WebElement passwordElement;
    WebElement submitElement;
    boolean incompleteEmail = false;



    @Given("User is on Login Page")
    public void visitWebsite()
    {
        browserDriver.get("https://www.automationexercise.com"); // this will go to the homepage
        browserDriver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a")).click();
    }
    @When("User will login to the website using {string} username and {string} password")
    public void loginUser(String username, String password)
    {
         userElement = browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/input[2]"));
         passwordElement = browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/input[3]"));
         submitElement = browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/button"));
        userElement.sendKeys(username);
        passwordElement.sendKeys(password);
        incompleteEmail = browserDriver.findElement(By.cssSelector("input[type='email']")).getAttribute("value").contains("@");
        submitElement.click();
    }
    @Then("User will know if they are login if they can see the product page and have a result of {string}")
    public void verifyLogin(String expectedAnswer)
    {
        if(!incompleteEmail)
        {
            assertEquals(expectedAnswer,"Please Insert email Address");
            return;
        }
        try
        {
           browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/p")).isDisplayed();
            assertEquals(expectedAnswer,"Fail");
        }
        catch (NoSuchElementException e)
        {
            if(browserDriver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[10]/a")).isDisplayed())
            {
                assertEquals(expectedAnswer,"Success");
                System.out.println("Login Verified");
            }
        }

    }
    @After()
    public void closeBrowser() {
        browserDriver.quit();
    }




}
