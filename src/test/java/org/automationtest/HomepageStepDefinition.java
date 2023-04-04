package org.automationtest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.automationtest.CustomLogger.CustomLogger;
import org.automationtest.WebNavigator.HomePage;
import org.automationtest.WebNavigator.LoginPage;
import org.automationtest.WebNavigator.utils.WebNavigatorHelper;
import org.openqa.selenium.By;

public class HomepageStepDefinition {

    HomePage homePage;



    /**
     * This Scenario/ Background is recurring
     */

    /**
     * Before each test log the scenario name
     * @param scenario  gets the current scenario
     */
    @Before
    public void setup(Scenario scenario)
    {
        CustomLogger.logInfo("Start Test of scenario "+ scenario.getName());
        WebNavigatorHelper.getInstance().getBrowserDriver();
    }

    /**
     * opens the browser and navigates, maximizes the window, and goes to the homepage
     *
     */
    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        CustomLogger.logInfo("Opening Webpage");

        WebNavigatorHelper.getInstance().getBrowserDriver().get("https://www.automationexercise.com"); // this will go to the homepage
        WebNavigatorHelper.getInstance().pauseExecution(500);
        WebNavigatorHelper.getInstance().getBrowserDriver().manage().window().maximize();
        homePage = new HomePage();
    }
    /**
     *goes to the login page
     *
     */

    @Then("I can Login")
    public void iCanLogin() {
        CustomLogger.logInfo("Going to Login page");
        homePage.clickLoginView();
    }

    /**
     * There are times that the session will end after every scenario, meaning it will go back to the homepage
     * but this tearDown method logs out the user and goes back to the homepage. Resetting for the next Scenario
     */
    @After
    public void tearDown(Scenario scenario)
    {
        CustomLogger.logInfo("Logging out user");

        WebNavigatorHelper.getInstance().getBrowserDriver().findElement(By.xpath("//a[@href='/logout']")).click();
        CustomLogger.logInfo("Finish Test of "+ scenario.getName());

    }

}
