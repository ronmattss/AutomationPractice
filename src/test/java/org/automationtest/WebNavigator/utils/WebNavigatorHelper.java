package org.automationtest.WebNavigator.utils;

import org.automationtest.CustomLogger.CustomLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class WebNavigatorHelper {
    private static WebNavigatorHelper instance = null;
    private WebDriver browserDriver;

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
        if (browserDriver == null) {
            setBrowserDriver();
        }
        return browserDriver;
    }

    /**
     * Sets up the browser driver instance
     */
    void setBrowserDriver() {
        browserDriver = new FirefoxDriver();
    }


    /**
     * Quits the browser driver instance
     */
    public void quitBrowserDriver() {
        if (browserDriver != null) {
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
    public void dismissAd() {
        System.out.println("Ad Test");
        CustomLogger.logWarning("Trying to dismiss ads");
        try {
            List<WebElement> iframes = browserDriver.findElements(By.tagName("iframe"));

            for (int i = 0; i < iframes.size(); i++) {
                browserDriver.switchTo().frame(i);
                System.out.println("frame: " + i);
                int adCount = browserDriver.findElements((By.xpath("//div[@id='ad_position_box']"))).size();
                if (adCount == 1) {
                    if (clickDismissButton()) {
                        break;
                    } else {
                        browserDriver.switchTo().frame("ad_iframe");
                        if (clickDismissButton()) {
                            break;
                        }
                    }
                }
                browserDriver.switchTo().parentFrame();
            }
        } catch (Exception e) {
            CustomLogger.logInfo("No popup ads dismissed");
        }

        // for some reason (maybe in my environment) sometimes switching to defaultContent does not work
        browserDriver.switchTo().parentFrame();
        pauseExecution(1000);
        browserDriver.switchTo().defaultContent();
    }

    /**
     * Pauses execution for a specified duration
     *
     * @param duration duration to pause in milliseconds
     */
    public void pauseExecution(long duration) {

        browserDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(duration));

    }

    /**
     * To make sure buttons works
     *
     * @param button   the button to be clicked
     * @param duration duration in milliseconds to wait
     */
    public void waitButton(WebElement button, long duration) {
        CustomLogger.logInfo("Simulating Button click: " + button.getText());
        WebDriverWait wait = new WebDriverWait(getBrowserDriver(), Duration.ofMillis(duration));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

}
