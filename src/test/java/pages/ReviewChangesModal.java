package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReviewChangesModal extends BasePage{

    private final By OK_BUTTON = By.id("confirmDiffSubmit");

    public ReviewChangesModal(WebDriver driver) {
        super(driver);
    }

    public ReviewChangesModal isOpened(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(OK_BUTTON));
        return this;
    }

    public TestCasesListPage confirmChanges(){
        driver.findElement(OK_BUTTON).click();
        return new TestCasesListPage(driver);
    }
}
