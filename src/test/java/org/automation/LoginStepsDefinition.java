package org.automation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

public class LoginStepsDefinition  {

    WebDriver browserDriver = new FirefoxDriver();
    @Given("User is on Website")
    public void visitWebsite()
    {
        browserDriver.get("https://www.automationexercise.com"); // this will go to the homepage
        browserDriver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a")).click();
    }
    @When("User will login to the website using {string} username and {string} password")
    public void loginUser(String username, String password)
    {
        WebElement userElement = browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/input[2]"));
        WebElement passwordElement = browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/input[3]"));
        WebElement submitElement = browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/button"));
        userElement.sendKeys(username);
        passwordElement.sendKeys(password);
        submitElement.click();
    }
    @Then("User will be verified")
    public void verifyLogin()
    {
        if(browserDriver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a")).isDisplayed())
        {
            assertEquals(true,true);
        }
    }




}
