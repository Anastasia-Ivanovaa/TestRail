package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

@Log4j2
public abstract class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    protected String url = PropertyReader.getProperty("URL");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void retryClick(String locator) {
        for (var i = 1; i <= 3; i++) {
            try {
                log.info("Try #'{}' to click on element with locator", i);
                driver.findElement(By.xpath(locator)).click();
                log.info("Clicking on the element was successful.");
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                log.info("Exception was thrown: try #'{}'", i);
            }
        }
    }
}

