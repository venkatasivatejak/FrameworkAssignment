package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.derivedproducts.DerivedProductsPageOne;
import pages.derivedproducts.DerivedProductsPageTwo;
import utils.TestContext;
import utils.WebDriverUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DerivedProduct1StepDefinitions {

    DerivedProductsPageOne derivedProductsPageOne;

    public DerivedProduct1StepDefinitions() {
        this.derivedProductsPageOne = TestContext.getDerivedProductsPageOne();
    }

    @Given("Launch DP1 Home Page")
    public void launchDPHomePage() {
        derivedProductsPageOne.launchPage();
    }

    @Then("verify number of slides present are {int}")
    public void verifyNumberOfSlidesPresentAre(int slideCount) {
        Assert.assertEquals(slideCount, derivedProductsPageOne.getSlideTitles().size(), "count of slides");
    }

    @Then("validate each slide title with expected test data")
    public void validateEachSlideTitleWithExpectedTestData(DataTable slideTitles) {
        List<String> expectedSlideTitles = slideTitles.asList().subList(1, slideTitles.asList().size());
        List<String> actualSlideTitles = derivedProductsPageOne.getAllSlideTitles();
        Assert.assertEqualsNoOrder(actualSlideTitles, expectedSlideTitles, "slide titles");
    }

    @Then("Count and validate duration of each slide with the expected duration")
    public void countAndValidateDurationOfEachSlideWithTheExpectedDuration(DataTable slideDurations) {
        List<Map<String, String>> expectedSlideDurations = slideDurations.asMaps(String.class, String.class);
        SoftAssert softAssert = new SoftAssert();
        for (Map<String, String> expectedSlideDuration : expectedSlideDurations) {
            String slideTitle = expectedSlideDuration.get("Slide Title");
            Long actualDuration = derivedProductsPageOne.getEachSlideDuration(slideTitle);
            softAssert.assertEquals(actualDuration, Long.valueOf(expectedSlideDuration.get("Duration")), slideTitle + "duration");
        }
        softAssert.assertAll();
    }
}
