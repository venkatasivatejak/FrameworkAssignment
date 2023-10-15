package utils;

import io.cucumber.java.Scenario;
import org.aeonbits.owner.ConfigFactory;
import pages.coreproduct.CoreProductsPage;
import pages.derivedproducts.DerivedProductsPageOne;
import pages.derivedproducts.DerivedProductsPageTwo;

public final class TestContext {

    private Scenario scenario;
    public CoreProductsPage coreProductsPage;
    public DerivedProductsPageOne derivedProductsPage1;
    public DerivedProductsPageTwo derivedProductsPage2;
    private ApplicationPropertyConfig appConfig;

    private TestContext() {

    }

    private static ThreadLocal<TestContext> testContext = ThreadLocal.withInitial(() -> new TestContext());


    public static TestContext get() {
        return testContext.get();
    }


    public void initializeTestContext() {
        initiateAppConfig();
        WebDriverUtils.initializeDriver();
        coreProductsPage = new CoreProductsPage();
        derivedProductsPage1 = new DerivedProductsPageOne();
        derivedProductsPage2 = new DerivedProductsPageTwo();
    }

    private void initiateAppConfig() {
        appConfig = ConfigFactory.create(ApplicationPropertyConfig.class);
    }

    public static ApplicationPropertyConfig getAppConfig() {
        return get().appConfig;
    }

    public static void setScenario(Scenario s) {
        get().scenario = s;
    }

    public static Scenario getScenario() {
        return get().scenario;
    }

    public static CoreProductsPage getCoreProductsPage() {
        return get().coreProductsPage;
    }

    public static DerivedProductsPageTwo getDerivedProductsPageTwo() {
        return get().derivedProductsPage2;
    }

    public static DerivedProductsPageOne getDerivedProductsPageOne() {
        return get().derivedProductsPage1;
    }


    public void closeSession() {
        WebDriverUtils.quitDriver();
    }

}
