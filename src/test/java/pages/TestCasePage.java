package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;

@Log4j2
public class TestCasePage extends BasePage {

    private final By TEST_CASE_TITLE = By.xpath("//div[@data-testid='testCaseContentHeaderTitle']");
    private final By SUCCESS_MESSAGE = By.xpath("//div[@data-testid='messageSuccessDivBox']");
    private final By EDIT_BUTTON = By.xpath("//a[@data-testid='testCaseEditButton']");
    private final String TEST_CASE_VALUE = "//label[text()='%s']//parent::td";

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

    public void getTestCaseValue(String nameField) {
        By value = By.xpath(String.format(TEST_CASE_VALUE, nameField));
        WebElement element = driver.findElement(value);
        String text = element.getText();
        System.out.println(text);

//        ArrayList<WebElement> testCasesValues = new ArrayList<>(driver.findElements(value));


    }
}
