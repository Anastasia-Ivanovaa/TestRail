package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectViewPage extends BasePage {

    private final By ADD_TEST_CASE_BUTTON = By.id("sidebar-cases-add");
    private final String TAB_NAME_PATTERN = "//li//ancestor::div[@id='header']//a[text()='%s']";

    public ProjectViewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open {tabName} tab")
    public void switchTab(String tabName) {
        By tab = By.xpath(String.format(TAB_NAME_PATTERN, tabName));
        driver.findElement(tab).click();
    }

    @Step("Click on Add button in sidebar menu")
    public AddTestCasePage clickAddTestCaseInSidebar() {
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
        return new AddTestCasePage(driver);
    }
}
