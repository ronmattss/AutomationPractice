package org.automationtest.WebNavigator.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private static WebDriver browserDriver;

    public static WebDriver getDriver() {
        if (browserDriver == null) {
            browserDriver = new FirefoxDriver();
        }
        return browserDriver;
    }

    public static void quitDriver() {
        if (browserDriver != null) {
            browserDriver.quit();
            browserDriver = null;
        }
    }
}
