package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;

public class ProjectsPage extends BasePage {

    private final By SUCCESS_MESSAGE = By.xpath("//div[@class='message message-success']");
    private final By PROJECT_NAME = By.xpath("//a//ancestor::tr");
    private final String REMOVE_PROJECT_PATTERN = "//a[text()= '%s']//ancestor::tr//div[@data-testid='projectDeleteButton']";

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the text message")
    public String getSuccessMessage() {
        return driver.findElement(SUCCESS_MESSAGE).getText();
    }

    @Step("Check that project {projectName} is shown in projects list")
    public boolean isProjectExisting(String projectName) {

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
    public ConfirmationDeleteModal deleteProject(String projectName) {
        By deleteProject = By.xpath(String.format(REMOVE_PROJECT_PATTERN, projectName));
        driver.findElement(deleteProject).click();
        return new ConfirmationDeleteModal(driver);
    }
}
