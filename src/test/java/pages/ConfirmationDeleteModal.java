package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationDeleteModal extends BasePage {

    private final By OK_BUTTON = By.xpath("//a[@data-testid='caseFieldsTabDeleteDialogButtonOk']");
    private final By CANCEL_BUTTON = By.xpath("//a[@data-testid='deleteCaseDialogActionSecondary']");
    private final By YES_CHECKBOX = By.xpath(
            "//label/input[@data-testid='deleteCheckBoxTestId']//ancestor::div[@data-testid='caseFieldsTabDeleteDialogCheckbox']");

    public ConfirmationDeleteModal(WebDriver driver) {
        super(driver);
    }

    @Step("Confirm project deleting")
    public void confirmAndDeleteProject() {
        driver.findElement(YES_CHECKBOX).click();
        driver.findElement(OK_BUTTON).click();
    }
}
