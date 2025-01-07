package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private final By TAB_TITLE = By.id("navigation-dashboard");
    private final By ADD_PROJECT_BUTTON = By.xpath("//a[@data-testid='sidebarProjectsAddButton']");
    private final String PROJECT_NAME_PATTERN = "//tr[contains(@class,'project')]//child::a[text()='%s']";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Dashboard page is opened")
    public String getPageElement() {
        return driver.findElement(TAB_TITLE).getText();
    }

    @Step("Click on 'Add project' button")
    public AddProjectPage createProject() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new AddProjectPage(driver);
    }

    @Step("Open project from Dashboard page")
    public ProjectViewPage openProject(String projectName) {
        By project = By.xpath(String.format(PROJECT_NAME_PATTERN, projectName));
        driver.findElement(project).click();
        return new ProjectViewPage(driver);
    }
}
