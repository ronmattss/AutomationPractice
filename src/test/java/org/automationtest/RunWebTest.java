package org.automationtest;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/automationtest",
        glue = {"org/automationtest"},
        plugin = {"usage", "json:target/cucumber-reports/Cucumber.json", "me.jvt.cucumber.report.PrettyReports:target/cucumber-reports"},
        monochrome = true
)
public class RunWebTest {

}
