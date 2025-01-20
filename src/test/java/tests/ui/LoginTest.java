package tests.ui;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Login into the application", description = "Check positive login")
    @Epic("Login module")
    @Story("As a user I want to get Dashboard page after login into application")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://support.testrail.com/hc/en-us/articles/7076810203028-Introduction-to-TestRail#Thedashboard")
    @Owner("Anastasia Ivanova")
    @Description("Check positive login")
    public void checkValidLogin() {
        loginPage.open()
                .login(email, password);
        assertEquals(dashboardPage.getPageElement(),
                "DASHBOARD",
                "Dashboard page is NOT opened.");
    }

    @DataProvider(name = "LoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", password, "Email/Login is required."},
                {email, "", "Password is required."},
                {email, "123456", "Sorry, there was a problem.\n" +
                        "Email/Login or Password is incorrect. Please try again."}
        };
    }

    @Test(dataProvider = "LoginData", testName = "Invalid login data", description = "Check that user cannot login with invalid data")
    @Description("Negative login check")
    public void checkInvalidLogin(String email, String password, String expectedMessage) {
        loginPage.open()
                .login(email, password);
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, expectedMessage, "The message is not correct");
    }
}
