package pages;

import dto.TestCase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import wrappers.Picklist;
import wrappers.PicklistTC;
import wrappers.Textarea;

public class AddTestCasePage extends BasePage {

    private final By ADD_TEST_CASE_BUTTON = By.id("accept");
    private final By TEST_CASE_TITLE = By.id("title");

    public AddTestCasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill the fields for creating a new TEST CASE '{testCaseTitle}'")
    public TestCasePage fillForm(TestCase testCase, String testCaseTitle) {
        driver.findElement(TEST_CASE_TITLE).sendKeys(testCaseTitle);
        if (testCase.getSectionOption() != null) {
            new PicklistTC(driver, "Section").select(testCase.getSectionOption());
        }
        if (testCase.getSectionOption() != null) {
            new PicklistTC(driver, "Section").select(testCase.getSectionOption());
        }
        if (testCase.getTemplateOption() != null) {
            new PicklistTC(driver, "Template").select(testCase.getTemplateOption());
        }
        if (testCase.getTypeOption() != null) {
            new PicklistTC(driver, "Type").select(testCase.getTypeOption());
        }
        if (testCase.getPriorityOption() != null) {
            new PicklistTC(driver, "Priority").select(testCase.getPriorityOption());
        }
        if (testCase.getAutomationTypeOption() != null) {
            new PicklistTC(driver, "Automation Type").select(testCase.getAutomationTypeOption());
        }
        if (testCase.getPreconditions() != null) {
            new Textarea(driver, "Preconditions").write(testCase.getPreconditions());
        }
        if (testCase.getSteps() != null) {
            new Textarea(driver, "Steps").write(testCase.getSteps());
        }
        if (testCase.getExpectedResult() != null) {
            new Textarea(driver, "Expected Result").write(testCase.getExpectedResult());
        }
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
        return new TestCasePage(driver);
    }
}
