package pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class ConfirmationDeleteProjectModal extends BasePage {

    private final By OK_BUTTON = By.xpath("//a[@data-testid='caseFieldsTabDeleteDialogButtonOk']");
    private final By CANCEL_BUTTON = By.xpath("//a[@data-testid='deleteCaseDialogActionSecondary']");
    private final By YES_CHECKBOX = By.xpath(
            "//label/input[@data-testid='deleteCheckBoxTestId']//ancestor::div[@data-testid='caseFieldsTabDeleteDialogCheckbox']");

    public ConfirmationDeleteProjectModal(WebDriver driver) {
        super(driver);
    }

    @Step("Confirm project deleting")
    public void confirmAndDeleteProject() {
        log.info("Confirming the deletion of project");
        driver.findElement(YES_CHECKBOX).click();
        driver.findElement(OK_BUTTON).click();
    }
}
