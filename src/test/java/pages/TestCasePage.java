package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestCasePage extends BasePage {

    private final By TEST_CASE_TITLE = By.xpath("//div[@data-testid='testCaseContentHeaderTitle']");
    private final By SUCCESS_MESSAGE = By.xpath("//div[@data-testid='messageSuccessDivBox']");
    private final By EDIT_BUTTON = By.xpath("//a[@data-testid='testCaseEditButton'");

    public TestCasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the text message")
    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));
        return driver.findElement(SUCCESS_MESSAGE).getText();
    }

    @Step("Get the title of created test case")
    public String getTestCaseTitle() {
        return driver.findElement(TEST_CASE_TITLE).getText();
    }
}
