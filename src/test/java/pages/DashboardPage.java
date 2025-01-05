package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private final By TAB_TITLE = By.id("navigation-dashboard");
    private final By ADD_PROJECT_BUTTON = By.xpath("//a[@data-testid='sidebarProjectsAddButton']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open Dashboard page")
    public DashboardPage open() {
        driver.get("https://ivaonova.testrail.io/index.php?/dashboard");
        return this;
    }

    @Override
    @Step("Check that Dashboard page is opened")
    public DashboardPage isPageOpened() {
        driver.findElement(TAB_TITLE);
        return this;
    }

    @Step("Click on 'Add project' button")
    public AddProjectPage createProject(){
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new AddProjectPage(driver);
    }
}
