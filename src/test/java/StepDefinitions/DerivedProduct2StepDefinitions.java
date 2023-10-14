package StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.coreproduct.CoreProductsPage;
import pages.derivedproducts.DerivedProductsPageTwo;
import utils.TestContext;
import utils.WebDriverUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DerivedProduct2StepDefinitions {

    DerivedProductsPageTwo derivedProductsPageTwo;

    public DerivedProduct2StepDefinitions() {
        this.derivedProductsPageTwo = TestContext.getDerivedProductsPageTwo();
    }

    @Given("Launch DP2 Home Page")
    public void launchDPHomePage() {
        derivedProductsPageTwo.launchPage();
    }


    @And("Scroll down to the footer")
    public void scrollDownToTheFooter() {
        WebDriverUtils.scrollPageToBottom();
    }

    @Then("Find all the hyperlinks of the Footer links into a CSV file {string} and report duplicates")
    public void findAllTheHyperlinksOfTheFooterLinksIntoACSVFileAndReportDuplicates(String fileName) {
        derivedProductsPageTwo.writeFooterLinksToCSV(fileName);
    }


    @And("Embed CSV file {string} as attachment to the html report")
    public void embedCSVFileAsAttachmentToTheHtmlReport(String fileName) throws IOException {
        Path path = Paths.get(System.getProperty("user.dir") + "/" + fileName);
        TestContext.scenario.get().attach(Files.readAllBytes(path), "text/csv", "Footer Links CSV");
    }


}
