package org.automationtest.WebNavigator.utils;

import org.automationtest.CustomLogger.CustomLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class WebNavigatorHelper {
    private WebDriver browserDriver;


    private static WebNavigatorHelper instance = null;
    // Private constructor to prevent direct instantiation
    private WebNavigatorHelper() {
        setBrowserDriver();
    }
    /**
     * Returns a singleton instance of WebNavigatorHelper
     *
     * @return WebNavigatorHelper instance
     */
    public static WebNavigatorHelper getInstance() {
        if (instance == null) {
            instance = new WebNavigatorHelper();

        }
        return instance;
    }
    /**
     * Returns the browser driver instance
     *
     * @return WebDriver instance
     */
    public WebDriver getBrowserDriver() {
        if(browserDriver == null)
        {
            setBrowserDriver();
        }
        return browserDriver;
    }
    /**
     * Sets up the browser driver instance
     */
    void setBrowserDriver()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        browserDriver = new ChromeDriver(options);
    }
    /**
     * Quits the browser driver instance
     */


    public void quitBrowserDriver()
    {
        if(browserDriver != null)
        {
            browserDriver.quit();
        }
    }

    /**
     * Clicks the dismiss button on a popup ad
     *
     * @return boolean true if button is clicked, false otherwise
     */
    private boolean clickDismissButton() {
        if (browserDriver.findElements(By.xpath("//*[@id='dismiss-button']"))
                .size() == 1) {
            WebElement element = browserDriver.findElement(By.xpath("//*[@id='dismiss-button']"));
            JavascriptExecutor executor = (JavascriptExecutor) browserDriver;
            executor.executeScript("arguments[0].click();", element);
            return true;
        }
        return false;
    }

    /**
     * Dismisses popup ads in an iFrame
     *
     * @throws InterruptedException
     */
    public void dismissAd()
    {
        System.out.println("Ad Test");
        CustomLogger.logWarning("Trying to dismiss ads");
        try
        {
            List<WebElement> iframes = browserDriver.findElements(By.tagName("iframe"));

            for(int i = 0;i<iframes.size();i++)
            {
                browserDriver.switchTo().frame(i);
                System.out.println("frame: "+i);
                int adCount = browserDriver.findElements((By.xpath("//div[@id='ad_position_box']"))).size();
                if(adCount == 1)
                {
                    if(clickDismissButton())
                    {break;}
                    else
                    {
                        browserDriver.switchTo().frame("ad_iframe");
                        if(clickDismissButton())
                        {
                            break;
                        }
                    }
                }
                browserDriver.switchTo().parentFrame();
            }
        }
        catch (Exception e)
        {
            CustomLogger.logInfo("No popup ads dismissed");
        }
        browserDriver.switchTo().parentFrame();
        pauseExecution(1000);
        browserDriver.switchTo().defaultContent();
    }

    /**
     * Pauses execution for a specified duration
     *
     * @param duration duration to pause in milliseconds
     */
    public void pauseExecution(long duration)
    {
        try
        {
            Thread.sleep(duration);
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Tears down the WebNavigatorHelper instance
     */
    public void tearDownDriver()
    {
        System.out.println("tearing down current scenario");
        if(WebNavigatorHelper.getInstance().getBrowserDriver() != null)
        {
            try {
                WebNavigatorHelper.getInstance().getBrowserDriver().quit();
            } catch (NoSuchSessionException e) {
                System.out.println("Caught NoSuchSessionException while quitting the driver: " + e.getMessage());
            }
        }
    }
}
