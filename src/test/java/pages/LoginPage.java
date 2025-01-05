package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By EMAIL_INPUT = By.id("name");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("button_primary");
    private final By ERROR_MESSAGE = By.xpath("//div[contains(@class, 'loginpage-message')] ");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that Login page is opened")
    public LoginPage isPageOpened() {
        driver.findElement(LOGIN_BUTTON);
        return this;
    }

    @Step("Open Login page")
    public LoginPage open() {
        driver.get("https://ivaonova.testrail.io/");
        return this;
    }

    @Step("Login into app - email:{email} password:{password}")
    public DashboardPage login(String email, String password) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new DashboardPage(driver);
    }

    @Step("Get the error message")
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
