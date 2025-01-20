package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class TestCasePage extends BasePage {

    private final By TEST_CASE_TITLE = By.xpath("//div[@data-testid='testCaseContentHeaderTitle']");
    private final By SUCCESS_MESSAGE = By.xpath("//div[@data-testid='messageSuccessDivBox']");
    private final By EDIT_BUTTON = By.xpath("//a[@data-testid='testCaseEditButton']");
    private final String TEST_CASE_VALUE = "//label[text()='%s']//parent::td";
    private final By TEST_CASE_ID = By.xpath("//div[@data-testid='contentHeaderId']");

    public TestCasePage(WebDriver driver) {
        super(driver);
    }

    public TestCasePage isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EDIT_BUTTON));
        return this;
    }

    @Step("Get the text message")
    public String getSuccessMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Success message isn't appeared ");
        }
        return driver.findElement(SUCCESS_MESSAGE).getText();
    }

    @Step("Get the title of created test case")
    public String getTestCaseTitle() {
        log.info("Get the title of created test case");
        return driver.findElement(TEST_CASE_TITLE).getText();
    }

    @Step("Get value from {nameField} field")
    public String getTestCaseValue(String nameField) {
        log.info("Get the value from '{}' field", nameField);
        By value = By.xpath(String.format(TEST_CASE_VALUE, nameField));
        WebElement element = driver.findElement(value);
        String text = element.getText();
        String[] words = text.split("\n");
        return words[words.length - 1];
    }

    @Step("Get Test Case Id")
    public int getTestCaseId() {
        log.info("Get Test Case Id");
        String id = driver.findElement(TEST_CASE_ID).getText();
        int result = Integer.parseInt(id.substring(1));
        return result;
    }
}
