package pages;

import dto.EditTestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Checkbox;
import wrappers.Input;
import wrappers.Picklist;
import wrappers.Textarea;

@Log4j2
public class EditTestCasesSelectedPage extends BasePage {

    private final By PAGE_TITLE = By.xpath("//div[@data-testid='testCaseContentHeaderTitle']");
    private final By SAVE_TEST_CASES_BUTTON = By.id("accept");

    public EditTestCasesSelectedPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that the page is opened")
    public EditTestCasesSelectedPage isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("The page is NOT opened");
        }
        return this;
    }

    @Step("Fill editing form")
    public ReviewChangesModal fillEditForm(EditTestCase editTestCase) {
        log.info("Edit the values of test case");
        wait.until(ExpectedConditions.elementToBeClickable(SAVE_TEST_CASES_BUTTON));
        if (editTestCase.getTitle() != null) {
            new Checkbox(driver, "Title").click();
            new Input(driver, "Title").write(editTestCase.getTitle());
        }
        if (editTestCase.getSection() != null) {
            new Checkbox(driver, "Section").click();
            new Picklist(driver, "Section").selectEdit(editTestCase.getSection());
        }
        if (editTestCase.getTemplate() != null) {
            new Checkbox(driver, "Template").click();
            new Picklist(driver, "Template").selectEdit(editTestCase.getTemplate());
        }
        if (editTestCase.getType() != null) {
            new Checkbox(driver, "Type").click();
            new Picklist(driver, "Type").selectEdit(editTestCase.getType());
        }
        if (editTestCase.getPriority() != null) {
            new Checkbox(driver, "Priority").click();
            new Picklist(driver, "Priority").selectEdit(editTestCase.getPriority());
        }
        if (editTestCase.getAssignedTo() != null) {
            new Checkbox(driver, "Assigned To").click();
            new Picklist(driver, "Assigned To").selectEdit(editTestCase.getAssignedTo());
        }
        if (editTestCase.getEstimate() != null) {
            new Checkbox(driver, "Estimate").click();
            new Input(driver, "Estimate").write(editTestCase.getEstimate());
        }
        if (editTestCase.getReferences() != null) {
            new Checkbox(driver, "References").click();
            new Input(driver, "References").write(editTestCase.getReferences());
        }
        if (editTestCase.getAutomationType() != null) {
            new Checkbox(driver, "Automation Type").click();
            new Picklist(driver, "Automation Type").selectEdit(editTestCase.getAutomationType());
        }
        if (editTestCase.getPreconditions() != null) {
            new Checkbox(driver, "Preconditions").click();
            new Textarea(driver, "Preconditions").writeEdit(editTestCase.getPreconditions());
        }
        if (editTestCase.getSteps() != null) {
            new Checkbox(driver, "Steps").click();
            new Textarea(driver, "Steps").writeEdit(editTestCase.getSteps());
        }
        if (editTestCase.getExpectedResult() != null) {
            new Checkbox(driver, "Expected Result").click();
            new Textarea(driver, "Expected Result").writeEdit(editTestCase.getExpectedResult());
        }
        driver.findElement(SAVE_TEST_CASES_BUTTON).click();
        return new ReviewChangesModal(driver);
    }
}
