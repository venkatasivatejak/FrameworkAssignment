package pages.coreproduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.TestContext;
import utils.WebDriverUtils;

import java.util.List;

public class CoreProductsPage {

    public static By close_modal_dialog = By.cssSelector("div[class='p-2 absolute right-3 hover:cursor-pointer']");
    public static By video_feeds_time = By.xpath("//ul[.//*[@name='video']]//time");
    public static By additional_menu_header = By.xpath("//a[contains(@class,'style__link_2QXEv')]//span[contains(text(),'...')]");
    public static By news_features_menu_item = By.cssSelector("li[role='menuitem'] li[role='menuitem'] a[title='News & Features']");

    /**
     * Launch the Core Products web page
     */

    public void launchPage() {
        WebDriverUtils.openWebPage(TestContext.getAppConfig().cp1Url());
    }

    /**
     * Closes the Pre sales Ticket Access Modal Dialog
     */
    public void closeModalDialog() {
        WebDriverUtils.getDriver().findElement(close_modal_dialog).click();
    }


    /**
     * Navigates to the Addition Menu Items -  News & Feature
     */
    public void navigateToNewsAndFeatures() {
        Actions mouseAction = new Actions(WebDriverUtils.getDriver());
        WebElement additional_menu_header_ele = WebDriverUtils.waitForElement(additional_menu_header);
        mouseAction.moveToElement(additional_menu_header_ele).build().perform();
        WebDriverUtils.click(news_features_menu_item);
        WebDriverUtils.waitForPageLoad();
    }

    /**
     * @param text
     * Extracts  the number value from string
     * @return Integer Value String
     */

    private int getIntegerValue(String text) {
        return Integer.valueOf(text.replaceAll("[^0-9]", ""));
    }

    /**
     * Returns the count of all video fees under News & Features
     *
     * @return -
     */
    public int getCountOfAllVideoFeeds() {
        List<WebElement> videoFeedPostedTimings = WebDriverUtils.getDriver().findElements(video_feeds_time);
        return videoFeedPostedTimings.size();
    }

    /**
     * Returns the count of all videos posted before days
     *
     * @param days - Number days
     * @return count of video feeds
     */
    public int getAllVideosFeedsBeforeNDays(int days) {
        int count = 0;
        List<WebElement> videoFeedPostedTimings = WebDriverUtils.getDriver().findElements(video_feeds_time);
        for (WebElement videoFeedPostedTiming : videoFeedPostedTimings) {
            String label = videoFeedPostedTiming.getAttribute("aria-label");
            if (label.contains("d")) {
                int dayPostedBefore = getIntegerValue(label);
                if (dayPostedBefore >= days)
                    count = count + dayPostedBefore;
            }

        }
        return count;
    }

}
