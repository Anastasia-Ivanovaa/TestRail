package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DashboardPage extends BasePage {

    private final By TAB_TITLE = By.id("navigation-dashboard");
    private final By ADD_PROJECT_BUTTON = By.xpath("//a[@data-testid='sidebarProjectsAddButton']");
    private final String PROJECT_NAME_PATTERN = "//tr[contains(@class,'project')]//child::a[text()='%s']";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Dashboard page is opened")
    public String getPageElement() {
        log.info("Find tab title on Dashboard page");
        return driver.findElement(TAB_TITLE).getText();
    }

    @Step("Click on 'Add project' button")
    public AddProjectPage createProject() {
        log.info("Create project");
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new AddProjectPage(driver);
    }

    @Step("Open project from Dashboard page")
    public ProjectViewPage openProject(String projectName) {
        log.info("Open project '{}'",projectName);
        By project = By.xpath(String.format(PROJECT_NAME_PATTERN, projectName));
        driver.findElement(project).click();
        return new ProjectViewPage(driver);
    }
}
