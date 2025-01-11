package tests;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class LoginTest extends BaseTest {

    @Test(testName = "Login into the application", description = "Check positive login")
    @Description("Check positive login")
    public void validLogin() {
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
    public void invalidLogin(String email, String password, String expectedMessage) {
        loginPage.open()
                .login(email, password);
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, expectedMessage, "The message is not correct");
    }
}
