package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class DeletePermanentlyModal extends BasePage {

    private final By DELETE_PERMANENTLY_BUTTON =
            By.xpath("//div[contains(@class,'button-group')]//a[@data-testid='deleteCaseDialogActionDefault'][contains(text(),'Delete Permanently')]");

    public DeletePermanentlyModal(WebDriver driver) {
        super(driver);
    }

    @Step("Delete Permanently Modal is opened")
    public DeletePermanentlyModal isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_PERMANENTLY_BUTTON));
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            Assert.fail("Confirmation delete test case modal in NOT opened");
        }
        return this;
    }

    @Step("Click on DELETE PERMANENTLY button")
    public TestCasesListPage clickDelete() {
        log.info("Click on Delete Permanently button");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(DELETE_PERMANENTLY_BUTTON))).click();
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Delete button is NOT found");
        }
        return new TestCasesListPage(driver);
    }
}
