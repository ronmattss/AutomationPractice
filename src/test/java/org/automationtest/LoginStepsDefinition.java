package org.automationtest;

import io.cucumber.java.After;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automationtest.WebNavigator.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

public class LoginStepsDefinition  {

    WebDriver browserDriver = new FirefoxDriver();
    LoginPage loginPage;


    @Given("I am on the Login Page")
    public void userIsOnLogin_page()
    {
        browserDriver.get("https://www.automationexercise.com"); // this will go to the homepage
        browserDriver.findElement(By.xpath("//a[@href='/login']")).click();

        loginPage = new LoginPage(browserDriver,By.xpath("//input[@type='email']"),By.xpath("//input[@type='password']"),By.xpath("//button[text()='Login']"));

    }
    @When("I login using {string} as my username and {string} as my password")
    public void theUserLogsInWithUsernameAndPassword(String username, String password)
    {
        loginPage.fillInUsername(username);
        loginPage.filInPassword(password);
        loginPage.clickLogin();
    }

    // Change Description
    @Then("I should see  the login result as {string}")
    public void userShouldSeeTheirUsernameAndTheLoginResultShouldBe(String expectedAnswer)
    {
        assertEquals(expectedAnswer,String.valueOf(loginPage.verifyLogin()));
    }
    @After()
    public void closeBrowser() {
        browserDriver.quit();
    }


}
