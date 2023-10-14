package StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.coreproduct.CoreProductsPage;
import utils.TestContext;

public class CoreProductStepDefinitions {

    CoreProductsPage coreProductsPage;

    @Before
    public void setScenario(Scenario sc){
        TestContext.scenario.set(sc);
    }

    public CoreProductStepDefinitions() {
        this.coreProductsPage = TestContext.getCoreProductsPage();
    }

    @Given("Launch Core Product Home Page")
    public void launchCoreProductHomePage() {
        coreProductsPage.launchPage();
        coreProductsPage.closeModalDialog();
    }

    @And("Navigate To New & Features")
    public void navigateToNewFeatures() {
        coreProductsPage.navigateToNewsAndFeatures();
    }

    @And("Count total number of Videos Feeds and print to report")
    public void countTotalNumberOfVideosFeeds() {
        int count = coreProductsPage.getCountOfAllVideoFeeds();
        TestContext.scenario.get().log("Total Number Of Video Feeds" + count);

    }

    @And("count the videos feeds those are present in the page >= {int}")
    public void countTheVideosFeedsThoseArePresentInThePageD(int days) {
        int count = coreProductsPage.getAllVideosFeedsBeforeNDays(days);
        TestContext.scenario.get().log(String.format("Total Number Of Video Feeds >=%s-->%s", days, count));
    }
}
