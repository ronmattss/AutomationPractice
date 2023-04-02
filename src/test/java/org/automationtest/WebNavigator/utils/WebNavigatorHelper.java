package org.automationtest.WebNavigator.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class WebNavigatorHelper {
    private WebDriver browserDriver;

    private static WebNavigatorHelper instance = null;


    private WebNavigatorHelper() {
        setBrowserDriver();
    }

    public static WebNavigatorHelper getInstance() {
        if (instance == null) {
            instance = new WebNavigatorHelper();

        }
        return instance;
    }

    public WebDriver getBrowserDriver() {
        if(browserDriver == null)
        {
            setBrowserDriver();
        }
        return browserDriver;
    }

    void setBrowserDriver()
    {
        browserDriver = new FirefoxDriver();
    }

    public void quitBrowserDriver()
    {
        if(browserDriver != null)
        {
            browserDriver.quit();
        }
    }


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
     *  Function to dismiss popup ads in an iFrame
     * @throws InterruptedException
     */
    public void dismissAd() throws InterruptedException
    {
        System.out.println("Ad Test");
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
        browserDriver.switchTo().parentFrame();
        Thread.sleep(1000);
        browserDriver.switchTo().defaultContent();
    }

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

    public void setupDriver()
    {
        if (instance == null) {
            instance = new WebNavigatorHelper();
        }
    }
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
