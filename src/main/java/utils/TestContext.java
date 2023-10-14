package utils;

import io.cucumber.java.Scenario;
import pages.coreproduct.CoreProductsPage;
import pages.derivedproducts.DerivedProductsPageOne;
import pages.derivedproducts.DerivedProductsPageTwo;

public class TestContext {

    public static ThreadLocal<Scenario> scenario = new ThreadLocal<Scenario>();

    public static CoreProductsPage coreProductsPage;
    public static DerivedProductsPageOne derivedProductsPage1;
    public static DerivedProductsPageTwo derivedProductsPage2;


    public static void initializeTestContext() {
        WebDriverUtils.initializeDriver();
        coreProductsPage = new CoreProductsPage();
        derivedProductsPage1 = new DerivedProductsPageOne();
        derivedProductsPage2 = new DerivedProductsPageTwo();
    }

    public static CoreProductsPage getCoreProductsPage() {
        return coreProductsPage;
    }

    public static DerivedProductsPageTwo getDerivedProductsPageTwo() {
        return derivedProductsPage2;
    }

    public static DerivedProductsPageOne getDerivedProductsPageOne() {
        return derivedProductsPage1;
    }


    public static void closeSession() {
        WebDriverUtils.closeSession();
    }

}
