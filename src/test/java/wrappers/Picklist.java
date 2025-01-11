package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Picklist {

    WebDriver driver;
    String label;
    String pickListPattern = "//div[contains(@class,'column')]/label[normalize-space(text())='%s']//following-sibling::div";

    public Picklist(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        driver.findElement(By.xpath(String.format(pickListPattern, label)))
                .click();
        driver.findElement(By.xpath(String.format(pickListPattern + "//li[contains(text(),'%s')]", label,
                option))).click();
    }
}

