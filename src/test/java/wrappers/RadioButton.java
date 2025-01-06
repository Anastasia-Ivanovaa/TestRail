package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RadioButton {

    WebDriver driver;
    String label;

    public RadioButton(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void click() {
        driver.findElement(By.xpath(String.format("//input[@type='radio']/preceding::strong[text()='%s']", label))).click();
    }
}
