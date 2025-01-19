package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ConfirmationDeleteTestCaseModal extends BasePage {

    private final String BUTTON =
            "//div[@id='dialog-ident-casesDeletionDialog']/div//a[contains(@class,'button')][contains(text(),'%s')]";
    private final By TITLE_MODAL = By.id("ui-dialog-title-casesDeletionDialog");

    public ConfirmationDeleteTestCaseModal(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Confirmation Delete Test CaseModal is opened")
    public ConfirmationDeleteTestCaseModal isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_MODAL));
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            Assert.fail("Confirmation delete test case modal in NOT opened");
        }
        return this;
    }

    @Step("Click on button {buttonName}")
    public <T extends BasePage> T clickButton(String buttonName, Class<T> pageClass) {
        log.info("Click '{}' button", buttonName);
        By button = By.xpath(String.format(BUTTON, buttonName));
        driver.findElement(button).click();
        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
