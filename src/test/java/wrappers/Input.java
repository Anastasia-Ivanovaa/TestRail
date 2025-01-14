package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Input {

    WebDriver driver;
    String label;
    String INPUT_PATTERN = "//strong[contains(text(),'%s')]//ancestor::div[contains(@class, 'checkbox')]//following-sibling::input[@type='text']";

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        log.info("Enter text into '{}' field", label);
        driver.findElement(By.xpath(String.format(INPUT_PATTERN, label))).sendKeys(text);
    }
}
