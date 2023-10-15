package pages.derivedproducts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.TestContext;
import utils.WebDriverUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DerivedProductsPageTwo {

    public static By footer_links = By.xpath("//footer//a");


    public void launchPage() {
        WebDriverUtils.openWebPage(TestContext.getAppConfig().dp2Url());
    }

    public List<WebElement> getFooterLinkElements() {
        return WebDriverUtils.getDriver().findElements(footer_links);
    }

    public void writeFooterLinksToCSV(String fileName) {
        String filePath = System.getProperty("user.dir") + "/" + fileName;
        writeLinksToCSV(getFooterLinkElements(), filePath);
    }


    private void writeLinksToCSV(List<WebElement> links, String filePath) {
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            // Write CSV header
            csvWriter.append("Link Text,URL,IsDuplicate\n");
            List<String> urlList = new ArrayList<>();

            // Iterate through each link and write to CSV
            for (WebElement link : links) {
                String linkText = link.getText();
                String url = link.getAttribute("href");
                String isDuplicate = "no";
                if (urlList.contains(url)) {
                    isDuplicate = "yes";
                }
                // Write link details to CSV
                urlList.add(url);
                csvWriter.append(String.format("\"%s\",\"%s\",\"%s\"\n", linkText, url, isDuplicate));
            }

            System.out.println("Links written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
