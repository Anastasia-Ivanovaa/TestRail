package pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class ProjectViewPage extends BasePage {

    private final By ADD_TEST_CASE_BUTTON = By.id("sidebar-cases-add");
    private final String TAB_NAME_PATTERN = "//li//ancestor::div[@id='header']//a[text()='%s']";

    public ProjectViewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open {tabName} tab")
    public <T extends BasePage> T switchTab(String tabName, Class<T> pageClass) {
        log.info("Switching to '{}' tab", tabName);
        By setOption = By.xpath(String.format(TAB_NAME_PATTERN, tabName));
        driver.findElement(setOption).click();
        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Step("Click on Add button in sidebar menu")
    public AddTestCasePage clickAddTestCaseInSidebar() {
        log.info("Click on ADD TEST CASE button");
        driver.findElement(ADD_TEST_CASE_BUTTON).click();
        return new AddTestCasePage(driver);
    }
}
