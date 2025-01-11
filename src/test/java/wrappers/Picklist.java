package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Picklist {

    WebDriver driver;
    String label;
    String pickListPattern = "//div[contains(@class,'column')]/label[normalize-space(text())='%s']//following-sibling::div";

    public Picklist(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        log.info("Selecting '{}' into picklist {}", option, label);
        driver.findElement(By.xpath(String.format(pickListPattern, label)))
                .click();
        driver.findElement(By.xpath(String.format(pickListPattern + "//li[contains(text(),'%s')]", label,
                option))).click();
    }
}

