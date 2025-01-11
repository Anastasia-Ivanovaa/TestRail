package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationDeleteTestCaseModal extends BasePage {

    private final String MARK_AS_DELETED_BUTTON =
            "//div[@id='dialog-ident-casesDeletionDialog']/div//a[contains(@class,'button')][contains(text(),'%s')]";
     private final By TITLE_MODAL = By.id("ui-dialog-title-casesDeletionDialog");

    public ConfirmationDeleteTestCaseModal(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Confirmation Delete Test CaseModal is opened")
    public ConfirmationDeleteTestCaseModal isOpened() {
        driver.findElement(TITLE_MODAL);
        return this;
    }

    @Step("Click on button {buttonName}")
    public TestCasesListPage clickOnButton(String buttonName) {
        By button = By.xpath(String.format(MARK_AS_DELETED_BUTTON, buttonName));
        driver.findElement(button).click();
        return new TestCasesListPage(driver);
    }
}
