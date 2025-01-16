package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

@Log4j2
public class AddColumnModal extends BasePage {

    private final By ADD_COLUMN_BUTTON = By.id("addColumnSubmit");

    public AddColumnModal(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Add Column Modal is opened")
    public AddColumnModal isOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_COLUMN_BUTTON));
        } catch (TimeoutException e) {
            log.error(e.getMessage());
            Assert.fail("Add Column Modal is NOT opened");
        }
        return this;
    }

    @Step("Select '{optionName}' in Column dropdown")
    public AddColumnModal selectDropdownOption(String optionName) {
        log.info("Selecting '{}' into dropdown", optionName);
        WebElement selectElement = driver.findElement(By.name("addColumnItems"));
        Select select = new Select(selectElement);
        select.selectByContainsVisibleText(optionName);
        return this;
    }

    @Step("Click ADD COLUMN button")
    public SelectColumnsModal clickAddColumnButton() {
        log.info("Click ADD COLUMN button");
        driver.findElement(ADD_COLUMN_BUTTON).click();
        return new SelectColumnsModal(driver);
    }
}
