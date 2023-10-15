package TestRunners;

import org.testng.annotations.*;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.TestContext;

@CucumberOptions(features = "src/test/java/features", glue = {"StepDefinitions"},
        monochrome = true,
        plugin = {"html:target/cucumber.html", "json:target/cucumber.json", "rerun:target/failed_scenarios.txt"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

    @BeforeMethod(alwaysRun = true)
    public void initializeTestContext() {
        TestContext.get().initializeTestContext();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {

        return super.scenarios();
    }

    @AfterMethod(alwaysRun = true)
    public void closeSession() throws Exception {
        TestContext.get().closeSession();
    }

}
