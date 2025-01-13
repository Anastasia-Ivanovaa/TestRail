package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

@Log4j2
public class ProjectsPage extends BasePage {

    private final By SUCCESS_MESSAGE = By.xpath("//div[@class='message message-success']");
    private final By PROJECT_NAME = By.xpath("//a//ancestor::tr");
    private final String REMOVE_PROJECT_PATTERN = "//a[text()= '%s']//ancestor::tr//div[@data-testid='projectDeleteButton']";

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the text message")
    public String getSuccessMessage() {
        log.info("Getting of success message ");
        return driver.findElement(SUCCESS_MESSAGE).getText();
    }

    @Step("Check that project {projectName} is shown in projects list")
    public boolean isProjectExisting(String projectName) {
        log.info("Find the name of created project in the projects list");
        ArrayList<WebElement> projectsList = new ArrayList<>(driver.findElements(PROJECT_NAME));
        boolean result = false;
        for (WebElement nameProject : projectsList) {

            if (nameProject.getText().equals(projectName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Step("Click on DELETE button next to project {projectName}")
    public ConfirmationDeleteProjectModal deleteProject(String projectName) {
        log.info("Open Confirmation Delete Project Modal for '{}' project", projectName);
        By deleteProject = By.xpath(String.format(REMOVE_PROJECT_PATTERN, projectName));
        driver.findElement(deleteProject).click();
        return new ConfirmationDeleteProjectModal(driver);
    }
}
