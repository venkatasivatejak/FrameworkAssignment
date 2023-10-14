package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class WebDriverUtils {
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private WebDriverUtils() {

    }

    //throws MalformedURLException
    public static WebDriver getDriver() {
//        if (threadLocalDriver.get() == null) {
//            threadLocalDriver.set(new ChromeDriver());
//        }
        return threadLocalDriver.get();
    }

    public static void quitDriver() {
        if (threadLocalDriver.get() != null) {
            threadLocalDriver.get().quit();
            threadLocalDriver.remove();
        }
    }

    public static void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        ExpectedCondition<Boolean> jsLoad = (WebDriver driver) -> {
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        };
        wait.until(jsLoad);
    }

    public static void redirectToNewTab() {
        List<String> windowHandles = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(windowHandles.get(windowHandles.size() - 1));
    }

    /**
     * Scroll to the bottom of the page using JavaScript
     */
    public static void scrollPageToBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void openWebPage(String url) {
       getDriver().get(url);
       waitForPageLoad();
    }

    public static void initializeDriver(){
        if (threadLocalDriver.get() == null) {
            threadLocalDriver.set(new ChromeDriver());
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
           getDriver().manage().window().maximize();
        }

    }

    public static void closeSession(){
        getDriver().quit();
        threadLocalDriver.remove();
    }

}

