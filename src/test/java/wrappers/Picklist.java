package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Picklist {

    WebDriver driver;
    String label;
    String pickListPattern = "//label[normalize-space(text()) ='%s']/ancestor::div[@class='form-group']/div[1]";

    public Picklist(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        driver.findElement(By.xpath(String.format(pickListPattern, label)))
                .click();
        driver.findElement(By.xpath(String.format(pickListPattern + "//option[text()='%s']", label,
                 option))).click();
    }
}
