package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    private final By EMAIL_INPUT = By.id("name");
    private final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("button_primary");
    private final By ERROR_MESSAGE = By.xpath("//div[contains(@class, 'loginpage-message')] ");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Login page")
    public LoginPage open() {
        log.info("Login page is opened");
        driver.get(url);
        return this;
    }

    @Step("Login into app - email:{email} password:{password}")
    public DashboardPage login(String email, String password) {
        log.info("Log into application using credentials '{}' '{}'", email, password);
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new DashboardPage(driver);
    }

    @Step("Get the error message")
    public String getErrorMessage() {
        log.info("Get the error message");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
