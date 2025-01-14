package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Checkbox {

    WebDriver driver;
    String label;
    String CHECKBOX_PATTERN =
            "//strong[contains(text(),'%s')]//ancestor::div[contains(@class, 'checkbox')]//input[@type='checkbox']";

    public Checkbox(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void click() {
        log.info("Put tick into '{}' checkbox", label);
        driver.findElement(By.xpath(String.format(CHECKBOX_PATTERN, label))).click();
    }
}
