package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddProjectPage extends BasePage {

    private final By ADD_PROJECT_BUTTON = By.id("accept");
    private final By NAME_FIELD = By.id("name");
    private final By ANNOUNCEMENT_FIELD = By.id("announcement_display");
    private final By SHOW_ANNOUNCEMENT_CHECKBOX = By.id("show_announcement");
    private final String PROJECT_TYPE_PATTERN = "//input[@type='radio']/preceding::strong[text()='%s']";

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
    public void fillForm(String projectName, String announcement, String radioButtonName) {
        driver.findElement(NAME_FIELD).sendKeys(projectName);
        driver.findElement(ANNOUNCEMENT_FIELD).sendKeys(announcement);
        driver.findElement(SHOW_ANNOUNCEMENT_CHECKBOX).click();
        By button = By.xpath(String.format(PROJECT_TYPE_PATTERN, radioButtonName));
        driver.findElement(button).click();
        driver.findElement(ADD_PROJECT_BUTTON).click();
    }
}
