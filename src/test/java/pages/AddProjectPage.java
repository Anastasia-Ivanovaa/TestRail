package pages;

import dto.Project;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrappers.Input;
import wrappers.Picklist;
import wrappers.RadioButton;

public class AddProjectPage extends BasePage {

    private final By ADD_PROJECT_BUTTON = By.id("accept");
    private final String TAB_PATTERN =
            "//a[normalize-space(text())='%s']";

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open Add Project page")
    public AddProjectPage open() {
        driver.get("https://ivaonova.testrail.io/index.php?/admin/projects/add/1");
        return this;
    }

    @Override
    @Step("Check that Add Project page is opened")
    public AddProjectPage isPageOpened() {
        driver.findElement(ADD_PROJECT_BUTTON);
        return this;
    }

    @Step("Fill the fields for creating a new project '{projectName}'")
    public AddProjectPage fillForm(Project project) {

        new Input(driver, "Name").write(project.getName());
        new RadioButton(driver, "Use a single repository with baseline support").click();
//        By button = By.xpath(String.format(TAB_PATTERN, "Defects"));
//        driver.findElement(button).click();
//        new Picklist(driver, "Defect Plugin").select(project.getDefectPlugin());
        return this;
    }

    @Step("Click on ADD PROJECT button")
    public ProjectsPage clickAddProjectButton() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new ProjectsPage(driver);
    }
}
