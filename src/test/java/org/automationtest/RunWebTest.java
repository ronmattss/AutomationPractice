package org.automationtest;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/automationtest",
        glue= {"org/automationtest"},
        plugin = { "usage","html:target/cucumber-reports/Cucumber.htm","json:target/cucumber-reports/Cucumber.json"},
        monochrome = true
)
public class RunWebTest {

}
