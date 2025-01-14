package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Picklist {

    WebDriver driver;
    String label;
    String LOCATOR_CREATE_TESTCASE = "//div[contains(@class,'column')]/label[normalize-space(text())='%s']//following-sibling::div";
    String LOCATOR_EDIT_TESTCASE = "//strong[contains(text(),'%s')]//ancestor::div[contains(@class, 'checkbox')]//following-sibling::div";
    String OPTION = "//li[contains(text(),'%s')]";

    public Picklist(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void selectCreate(String option) {
        log.info("Selecting '{}' into picklist '{}'", option, label);
        driver.findElement(By.xpath(String.format(LOCATOR_CREATE_TESTCASE, label)))
                .click();
        driver.findElement(By.xpath(String.format(LOCATOR_CREATE_TESTCASE + OPTION, label,
                option))).click();
    }

    public void selectEdit(String option) {
        log.info("Selecting '{}' into picklist '{}'", option, label);
        driver.findElement(By.xpath(String.format(LOCATOR_EDIT_TESTCASE, label)))
                .click();
        driver.findElement(By.xpath(String.format(LOCATOR_EDIT_TESTCASE + OPTION, label,
                option))).click();
    }
}

