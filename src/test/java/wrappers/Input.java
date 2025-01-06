package wrappers;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Input {

    WebDriver driver;
    String label;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {

        driver
                .findElement(By.xpath(String.format("//label[normalize-space(text()) ='%s']/ancestor::div[@class='form-group']/input", label)))
                .sendKeys(text);
    }
}
