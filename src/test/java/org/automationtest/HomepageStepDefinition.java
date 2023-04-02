package org.automationtest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.HomePage;
import org.automationtest.WebNavigator.LoginPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;

public class HomepageStepDefinition {

    LoginPage loginPage;
    HomePage homePage;

    @Before
    public void setup()
    {
        CustomLogger.logInfo("Start Test");
        WebNavigatorHelper.getInstance().getBrowserDriver();
    }

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        CustomLogger.logInfo("Opening Webpage");

        WebNavigatorHelper.getInstance().getBrowserDriver().get("https://www.automationexercise.com"); // this will go to the homepage
        WebNavigatorHelper.getInstance().pauseExecution(500);
        WebNavigatorHelper.getInstance().getBrowserDriver().manage().window().maximize();
        homePage = new HomePage();
    }

    @Then("I can Login")
    public void iCanLogin() {
        homePage.clickLoginView();
    }
    @After
    public void tearDown()
    {
        CustomLogger.logInfo("Logging out user");

        WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/logout']")).click();
        CustomLogger.logInfo("Finish Test");

    }

}
