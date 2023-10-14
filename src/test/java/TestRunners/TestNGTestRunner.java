package TestRunners;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.TestContext;

@CucumberOptions(features = "src/test/java/features", glue = "StepDefinitions",
        monochrome = true,
        tags = "@DP1Test1",
        plugin = {"html:target/cucumber.html", "json:target/cucumber.json", "rerun:target/failed_scenarios.txt"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

    @BeforeMethod(alwaysRun = true)
    public void initializeTestContext() {
        TestContext.initializeTestContext();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterMethod(alwaysRun = true)
    public void closeSession() throws Exception {
        TestContext.closeSession();
    }

}
