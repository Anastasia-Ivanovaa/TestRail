package pages;

import dto.TestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wrappers.Picklist;
import wrappers.Textarea;

@Log4j2
public class AddTestCasePage extends BasePage {

    private final By ADD_TEST_CASE_BUTTON = By.id("accept");
    private final By TEST_CASE_TITLE = By.id("title");

    public AddTestCasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill the fields for creating a new TEST CASE '{testCaseTitle}'")
    public TestCasePage fillForm(TestCase testCase, String testCaseTitle) {
        log.info("Creating test case '{}' ", testCaseTitle);
        driver.findElement(TEST_CASE_TITLE).sendKeys(testCaseTitle);
        if (testCase.getSectionOption() != null) {
            new Picklist(driver, "Section").selectCreate(testCase.getSectionOption());
        }
        if (testCase.getSectionOption() != null) {
            new Picklist(driver, "Section").selectCreate(testCase.getSectionOption());
        }
        if (testCase.getTemplateOption() != null) {
            new Picklist(driver, "Template").selectCreate(testCase.getTemplateOption());
        }
        if (testCase.getTypeOption() != null) {
            new Picklist(driver, "Type").selectCreate(testCase.getTypeOption());
        }
        if (testCase.getPriorityOption() != null) {
            new Picklist(driver, "Priority").selectCreate(testCase.getPriorityOption());
        }
        if (testCase.getAutomationTypeOption() != null) {
            new Picklist(driver, "Automation Type").selectCreate(testCase.getAutomationTypeOption());
        }
        if (testCase.getPreconditions() != null) {
            new Textarea(driver, "Preconditions").writeCreate(testCase.getPreconditions());
        }
        if (testCase.getSteps() != null) {
            new Textarea(driver, "Steps").writeCreate(testCase.getSteps());
        }
        if (testCase.getExpectedResult() != null) {
            new Textarea(driver, "Expected Result").writeCreate(testCase.getExpectedResult());
        }
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
        return new TestCasePage(driver);
    }
}
