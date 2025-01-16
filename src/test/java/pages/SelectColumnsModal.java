package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@Log4j2
public class SelectColumnsModal extends BasePage {

    private final String UPDATE_COLUMNS_BUTTON = "//button[contains(text(),'%s')]";
    private final By ADD_COLUMN_BUTTON = By.id("selectColumnsAdd");

    public SelectColumnsModal(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Select Columns Modal is opened")
    public SelectColumnsModal isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_COLUMN_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Select Columns Modal is NOT opened");
        }
        return this;
    }

    @Step("Click on '{buttonName}' button in Select Columns Modal")
    public TestCasesListPage clickButton(String buttonName) {
        log.info("Click on '{}' button in Select Columns Modal",buttonName );
        retryClick(String.format(UPDATE_COLUMNS_BUTTON, buttonName));
        return new TestCasesListPage(driver);
    }

    @Step("Click on ADD COLUMN button in Select Columns Modal")
    public AddColumnModal clickAddColumnButton() {
        log.info("Click on ADD COLUMN button in Select Columns Modal");
        wait.until(ExpectedConditions.elementToBeClickable(ADD_COLUMN_BUTTON)).click();
        return new AddColumnModal(driver);
    }
}
