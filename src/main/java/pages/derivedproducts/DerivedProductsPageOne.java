package pages.derivedproducts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestContext;
import utils.WebDriverUtils;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class DerivedProductsPageOne {

    public static By slide_titles = By.cssSelector(".TileHeroStories_tileHeroStoriesButtonTitle__pKsws");
    public static String active_slide_xpath = "//h1[@aria-hidden='false' and contains(.,'%s')]";
    public static String passive_slide_xpath = "//h1[@aria-hidden='true' and contains(.,'%s')]";


    public void launchPage() {
        WebDriverUtils.openWebPage(TestContext.getAppConfig().dp1Url());
    }

    public List<WebElement> getSlideTitles() {
        return WebDriverUtils.getDriver().findElements(slide_titles);
    }

    public List<String> getAllSlideTitles() {
        List<String> titles = new ArrayList<>();
        titles = getSlideTitles().stream().map(ele -> ele.getText()).collect(Collectors.toList());
        return titles;
    }

    public long getEachSlideDuration(String slideTitle) {
        WebDriverWait wait = new WebDriverWait(WebDriverUtils.getDriver(), Duration.ofSeconds(60));
        //wait for the slide to be inactive
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(passive_slide_xpath, slideTitle))));
        //wait until the slide to be active
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(active_slide_xpath, slideTitle))));
        // Record the start time
        long startTime = System.currentTimeMillis();
        // Wait for the slide to be inactive with a timeout of 10 seconds
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(String.format(passive_slide_xpath, slideTitle))));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(String.format(passive_slide_xpath, slideTitle))));
        // Record the end time
        long endTime = System.currentTimeMillis();
        // return the slide duration
        return (endTime - startTime) / 1000;

    }

}
