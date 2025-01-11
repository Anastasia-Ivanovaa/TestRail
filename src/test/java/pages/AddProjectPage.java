package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddProjectPage extends BasePage {

    private final By ADD_PROJECT_BUTTON = By.id("accept");
    private final By NAME_FIELD = By.id("name");
    private final By SINGLE_REPOSITORY_FOR_ALL_CASES_BUTTON = By.id("suite_mode_single");

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill the fields for creating a new project '{projectName}'")
    public AddProjectPage fillForm(String projectName) {
        driver.findElement(NAME_FIELD).sendKeys(projectName);
        driver.findElement(SINGLE_REPOSITORY_FOR_ALL_CASES_BUTTON).click();
        return this;
    }

    @Step("Click on ADD PROJECT button")
    public ProjectsPage clickAddProjectButton() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new ProjectsPage(driver);
    }
}
