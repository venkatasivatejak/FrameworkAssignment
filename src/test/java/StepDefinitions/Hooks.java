package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.TestContext;
import utils.WebDriverUtils;

public class Hooks {

    @Before
    public void setScenario(Scenario sc) {
        TestContext.setScenario(sc);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot and embed it in the report
            byte[] screenshot = WebDriverUtils.getFailureScreenShot();
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
    }
}
