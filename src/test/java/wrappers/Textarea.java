package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Textarea {

    WebDriver driver;
    String label;
    String LOCATOR_CREATE_TESTCASE = "//label[text()[contains(., '%s')]]//ancestor::div[@class='form-group']//div[@role='textbox']";
    String LOCATOR_EDIT_TESTCASE = "//strong[contains(text(), '%s')]//ancestor::div[@class='form-group']//div[2]";

    public Textarea(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void writeCreate(String text) {
        log.info("Writing '{}' into {}", text, label);
        driver.findElement(By.xpath(String.format(LOCATOR_CREATE_TESTCASE, label))).sendKeys(text);
    }

    public void writeEdit(String text) {
        log.info("Writing '{}' into {}", text, label);
        driver.findElement(By.xpath(String.format(LOCATOR_EDIT_TESTCASE, label))).sendKeys(text);
    }


}
