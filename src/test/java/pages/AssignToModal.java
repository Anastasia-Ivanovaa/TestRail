package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import wrappers.Picklist;

@Log4j2
public class AssignToModal extends BasePage {

    private final By OK_BUTTON = By.id("addCommentSubmit");

    public AssignToModal(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Assign To Modal is opened")
    public AssignToModal isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(OK_BUTTON));
        } catch (TimeoutException e) {
            e.getMessage();
            Assert.fail("Assign To Modal is NOT opened");
        }
        return this;
    }

    @Step("Selecting '{option}' into picklist 'Assign To'")
    public TestCasesListPage setOptionInDropdown(String option) {
        new Picklist(driver, "Assign To").selectAssign(option);
        driver.findElement(OK_BUTTON).click();
        return new TestCasesListPage(driver);
    }
}
