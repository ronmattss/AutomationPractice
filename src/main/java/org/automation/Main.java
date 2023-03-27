package org.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Automator automator = new Automator();
        automator.visitWebsite("https://www.automationexercise.com");
        automator.fillLoginForm();

    }
}

 class Automator
{
    WebDriver browserDriver = new FirefoxDriver();
    public void visitWebsite(String website)
    {
        browserDriver.get(website); // this will go to the homepage
        browserDriver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a")).click();
    }

    public void fillLoginForm()
    {
         WebElement userElement = browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/input[2]"));
        WebElement passwordElement = browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/input[3]"));
        WebElement submitElement = browserDriver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/button"));
        userElement.sendKeys("ronmattss@gmail.com");
        passwordElement.sendKeys("@Ron192000");
        submitElement.click();
    }
}
