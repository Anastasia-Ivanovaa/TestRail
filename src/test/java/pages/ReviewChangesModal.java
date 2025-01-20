package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class ReviewChangesModal extends BasePage{

    private final By OK_BUTTON = By.id("confirmDiffSubmit");

    public ReviewChangesModal(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Review Changes Modal is opened")
    public ReviewChangesModal isOpened(){
        try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(OK_BUTTON));}
        catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Review Changes Modal is NOT opened");
        }
        return this;
    }

    @Step("Click on OK button in Review Changes Modal")
    public TestCasesListPage confirmChanges(){
        log.info("Click on OK button in Review Changes Modal");
        driver.findElement(OK_BUTTON).click();
        return new TestCasesListPage(driver);
    }
}
