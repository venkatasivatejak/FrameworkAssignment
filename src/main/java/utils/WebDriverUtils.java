package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WebDriverUtils {
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private WebDriverUtils() {

    }

    public enum Browser {
        CHROME,
        FIREFOX,
        EDGE;

        public static Browser fromString(String browserName) {
            for (Browser browser : Browser.values()) {
                if (browser.name().equalsIgnoreCase(browserName)) {
                    return browser;
                }
            }
            throw new IllegalArgumentException("No browser with name " + browserName + " found");
        }
    }

    public static void initializeDriver() {
        if (threadLocalDriver.get() == null) {
            Browser selectedBrowser = Browser.fromString(TestContext.getAppConfig().browser());
            WebDriver driver = null;
            if (selectedBrowser == Browser.CHROME) {
                driver = new ChromeDriver();
            } else if (selectedBrowser == Browser.FIREFOX) {
                driver = new FirefoxDriver();
            }
            threadLocalDriver.set(driver);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            getDriver().manage().window().maximize();
        }

    }


    //throws MalformedURLException
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        ExpectedCondition<Boolean> jsLoad = (WebDriver driver) -> {
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        };
        wait.until(jsLoad);
    }

    /**
     * Scroll to the bottom of the page using JavaScript
     */
    public static void scrollPageToBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * @param url - of web application
     *        Opens the web application and wait for the page to fully load
     */
    public static void openWebPage(String url) {
        getDriver().get(url);
        waitForPageLoad();
    }

    public static byte[] getFailureScreenShot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void quitDriver() {
        getDriver().quit();
        threadLocalDriver.remove();
    }

}

